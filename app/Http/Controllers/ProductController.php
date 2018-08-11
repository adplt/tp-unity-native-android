<?php

namespace App\Http\Controllers;

use App\DegreeModel;
use App\BranchModel;
use App\BranchDetailModel;
use App\FacultyModel;
use Illuminate\Http\Request;

use App\Http\Requests;
use Illuminate\Support\Facades\Validator;
use Illuminate\Support\Facades\Auth;

class ProductController extends Controller
{

    private function getAuthUser() {
        return Auth::user();
    }


    public function addProduct(Request $request)
    {
        $user = $this->getAuthUser();

        $validator = Validator::make($request->all(), [
            'FacultyName' => 'required',
            'DegreeName' => 'required',
            'description' => 'required'
        ]);
        if ($validator->fails()) {

            return redirect('/addProduct')->withErrors($validator)->withInput();
        }
        $faculty = new FacultyModel();
        $faculty->faculty_name = $request->FacultyName;
        $faculty->save();
        $faculty = FacultyModel::orderBy('id', 'desc')->take(1)->get()->first();

        $branch_detail = new BranchDetailModel();
        $branch_detail->id_faculty = $faculty->id;
        $branch_detail->id_branch = $user->id_branch;
        $branch_detail->save();

        $degree = new DegreeModel();
        $degree->degree_name = $request->DegreeName;
        $degree->note = $request->description;
        $degree->id_faculty = $faculty->id;
//        $degree->branch_id = $user->id_branch;
        $degree->save();
        return redirect()->action('ProductController@index', ['msg' => 'Product has saved successfully']);
    }

    function add()
    {
        $degrees = DegreeModel::all();
        return view('product.create', ['degrees' => $degrees]);
    }

    function index(Request $request)
    {
        $user = $this->getAuthUser();

        $productname = $request->productname;

        $degrees = DegreeModel::orderBy('degree_name', 'asc')
            ->join('faculty', 'degree.id_faculty', '=', 'faculty.id')
            ->join('branch_detail', 'faculty.id', '=', 'branch_detail.id_faculty')
            ->where('branch_detail.id_branch', $user->id_branch);

        if($productname != null && $productname.isNonEmptyString()) {
            $degrees = $degrees->where('degree_name', 'LIKE', '%'.$productname.'%');
        }
        $degrees = $degrees->get();

        $unique_faculties = [];
        $faculties_id = [];
        foreach($degrees as $degree) {

            if(!in_array($degree->id_faculty, $faculties_id)) {
                $obj = [
                    "faculty_id" => $degree->id_faculty,
                    "faculty_name" => $degree->faculty_name
                ] ;
                array_push($unique_faculties, $obj);
                array_push($faculties_id, $degree->id_faculty);
            }

        }

        return view('product.index', ['faculties' => $unique_faculties, 'degrees' => $degrees, 'productname' => $productname]);
    }

    function show($id)
    {
        //salah?
        $degree = FacultyModel::join('degree', 'degree.id_faculty', '=', 'faculty.id')->where('degree.id', $id)->first();

        return view('product.detail', ['degree' => $degree]);
    }

    function update($id)
    {

        $faculty = FacultyModel::all()->find($id);
        $degree = DegreeModel::all()->find($id);
        return view('product.update', ['degree' => $degree, 'faculty' => $faculty]);

        /*   $degree = DegreeModel::find($id);
           return view('product.update', ['degree' => $degree]);*/
    }

    function updateProduct(Request $request)
    {
        $degree = DegreeModel::find($request->id);
        $degree->degree_name = $request->ProductName;
        $degree->note = $request->description;

        $degree->save();
        //dd(json_encode($event));

        return redirect()->action('ProductController@index', ['msg' => 'Product has updated successfully']);
    }

    function destroy($id)
    {
        //FacultyModel::find($id)->delete();
        DegreeModel::find($id)->delete();
        return redirect('/product');
    }


}
