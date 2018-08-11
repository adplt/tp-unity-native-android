<?php

namespace App\Providers;

use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\Hash;
use Illuminate\Support\Facades\Validator;
use Illuminate\Support\ServiceProvider;

class AppServiceProvider extends ServiceProvider
{
    /**
     * Bootstrap any application services.
     *
     * @return void
     */
    public function boot()
    {
        Validator::extend('pass_check', function($attribute, $value, $parameters, $validator) {
            return Hash::check($value, Auth::user()->password);
        });
        Validator::replacer('pass_check', function($message, $attribute, $rule, $parameters) {
            return 'The current password is wrong.';
        });
        Validator::extend('pass_alpha_num', function($attribute, $value, $parameters, $validator) {
            return preg_match('/^(?=.*[0-9]+.*)(?=.*[a-zA-Z]+.*)[0-9a-zA-Z]{6,}$/', $value);
        });
        Validator::replacer('pass_alpha_num', function($message, $attribute, $rule, $parameters) {
            return 'The password must contain at least one letter, at least one number, and be longer than six characters.';
        });
    }

    /**
     * Register any application services.
     *
     * @return void
     */
    public function register()
    {
        //
    }
}
