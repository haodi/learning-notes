Nginx 学习笔记
=============

The [ngx_http_map_module](http://nginx.org/en/docs/http/ngx_http_map_module.html) module creates variables whose values depend on values of other variables.

Creates a new variable whose value depends on values of one or more of the source variables specified in the first parameter.

    Example Configuration
    map $http_host $name {
        hostnames;
    
        default       0;
    
        example.com   1;
        *.example.com 1;
        example.org   2;
        *.example.org 2;
        .example.net  3;
        wap.*         4;
    }

    map $http_user_agent $mobile {
        default       0;
        "~Opera Mini" 1;
    }