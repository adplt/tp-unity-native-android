@extends('layout.main')
@section('title','Product')
@section('content')

    <style>
        .my-tables > tbody > tr:first-child > th,
        .my-tables > tbody > tr:first-child > td {
            border-top: none !important;
        }

        .my-tables {
            margin: 0 !important;
        }

        .parent-table {
            cursor: pointer;
        }
    </style>

    <html>
    <body>

    <h2>Product</h2>
    @if(isset($_GET['msg']) && $_GET['msg'].isNonEmptyString())
        <div class="alert alert-success">
            <b>{{ $_GET['msg'] }}</b>
        </div>
    @endif
    <div class="row">

        <div class="col-md-2">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Search</h3>
                </div>
                <div class="panel-body">
                    <form method="get" action="{{url('/product')}}">
                        <div class="form-group">
                            <label for="inputName">Product Name</label>
                            <input type="text" id="inputName" class="form-control" name="productname" value="{{ $productname }}"/>
                        </div>
                        <button type="submit" class="btn btn-default">Search</button>

                    </form>
                </div>
            </div>
        </div>

        <div class="col-md-10">
            <div class="panel panel-default">
                <div class="panel-heading">Product List</div>

                <table class="table">
                    <thead>
                    <tr>
                        <th>No</th>
                        <th>Faculty</th>
                    </tr>
                    </thead>
                    <tbody>
                    <?php $i = 1; ?>
                    @foreach($faculties as $faculty)
                        <tr class="parent-table">
                            <th scope="row">{{$i . "."}}</th>
                            <td>{{$faculty["faculty_name"]}}</td>
                            <td></td>
                        </tr>
                        <?php
                            $cloneDegree = clone $degrees;
                            $getDegrees = $cloneDegree;
                            $anotherIndex = 1;
                        ?>
                        <tr class="child-table" style="display: none">
                            <td colspan="3" style="padding-left: 30px">
                                <table class="table my-tables">
                                    <tbody>
                                    @foreach($getDegrees as $degree)
                                        @if($degree->id_faculty == $faculty["faculty_id"])
                                        <tr>
                                            <th>{{$i . "." . $anotherIndex++}}</th>
                                            <td style="padding-left: 30px">{{$degree->degree_name}}</td>
                                            <td>
                                                <div class="btn-group btn-group-xs" role="group">
                                                    <a href="{{url('/product/'.$degree->id)}}" class="btn btn-default">
                                                        <span class="glyphicon glyphicon-eye-open" aria-hidden="true"> View</span>
                                                    </a>
                                                    <a href="{{url('/product/update/'.$degree->id)}}" class="btn btn-default">
                                                        <span class="glyphicon glyphicon-pencil" aria-hidden="true"> Edit</span>
                                                    </a>
                                                    <a href="{{url('/product/delete/'.$degree->id)}}" class="btn btn-default btn btn-delete">
                                                        <span class="glyphicon glyphicon-trash" aria-hidden="true"> Delete</span>
                                                    </a>
                                                </div>
                                            </td>
                                        </tr>
                                        @endif
                                    @endforeach
                                    </tbody>
                                </table>
                            </td>
                        </tr>
                        <?php $i += 1;?>
                    @endforeach
                    </tbody>
                </table>
            </div>
            {{--<div align="center">--}}
                {{--{!! $faculties->appends(['productname' => $productname])->render() !!}--}}
            {{--</div>--}}
            <a class="btn btn-default" href="{{url('/addProduct')}}" role="button"><i
                        class="glyphicon glyphicon-plus"></i>Add</a>
        </div>
    </div>

    </body>
    </html>
    <script>
        $(function () {
            $('.btn-delete').click(function () {
                if (confirm('Are you sure?')) {

                } else {
                    return false;
                }
            });

            var lastIndex = -1;

            $(document).on('click', '.parent-table', function() {
                if(lastIndex == $(this).index()) {
                    $(this).next('.child-table').hide();
                    lastIndex = -1;
                }
                else {
                    $('.child-table').hide();
                    $(this).next('.child-table').show('fast');
                    lastIndex = $(this).index();
                }
            });
        });
    </script>
@endsection