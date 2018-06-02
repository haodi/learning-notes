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
    
### Passive Health Checks

    For passive health checks, NGINX and NGINX Plus monitor transactions as they happen, and try to resume failed connections. If the transaction still cannot be resumed, NGINX and NGINX Plus mark the server as unavailable and temporarily stop sending requests to it until it is marked active again.
    
    The conditions under which an upstream server is marked unavailable are defined for each upstream server with parameters to the server directive in the upstream block:
    
    fail_timeout – Sets the time during which a number of failed attempts must happen for the server to be marked unavailable, and also the time for which the server is marked unavailable (default is 10 seconds).
    max_fails – Sets the number of failed attempts that must occur during the fail_timeout period for the server to be marked unavailable (default is 1 attempt).
    In the following example, if NGINX fails to send a request to a server or does not receive a response from it 3 times in 30 seconds, it marks the server as unavailable for 30 seconds:
    
    upstream backend {
        server backend1.example.com;
        server backend2.example.com max_fails=3 fail_timeout=30s;
    }
    Note that if there is only a single server in a group, the fail_timeout and max_fails parameters are ignored and the server is never marked unavailable.
    
    
    Server Slow Start
    A recently recovered server can be easily overwhelmed by connections, which may cause the server to be marked as unavailable again. Slow start allows an upstream server to gradually recover its weight from zero to its nominal value after it has been recovered or became available. This can be done with the slow_start parameter to the upstream server directive:
    
    upstream backend {
        server backend1.example.com slow_start=30s;
        server backend2.example.com;
        server 192.0.0.1 backup;
    }
    The time value sets the time for the server to recover its weight.
    
    Note that if there is only a single server in a group, the slow_start parameter is ignored and the server is never marked unavailable.
    
    #### fail的标准 ####
    
    Nginx 默认判断失败节点状态以connect refuse和time out状态为准，不以HTTP错误状态进行判断失败，因为HTTP只要能返回状态说明该节点还可以正常连接，
    所以nginx判断其还是存活状态；除非添加了proxy_next_upstream指令设置对404、502、503、504、500和time out等错误进行转到备机处理，
    在next_upstream过程中，会对fails进行累加，如果备用机处理还是错误则直接返回错误信息（但404不进行记录到错误数，如果不配置错误状态也不对其进行错误状态记录），
    综述，nginx记录错误数量只记录timeout 、connect refuse、502、500、503、504这6种状态，timeout和connect refuse是永远被记录错误状态，
    而502、500、503、504只有在配置proxy_next_upstream后nginx才会记录这4种HTTP错误到fails中，当fails大于等于max_fails时，则该节点失效.
    
    #### 探测机制 ####
    
    如果探测所有节点均失效，备机也为失效时，那么nginx会对所有节点恢复为有效，重新尝试探测有效节点，如果探测到有效节点则返回正确节点内容，
    如果还是全部错误，那么继续探测下去，当没有正确信息时，节点失效时默认返回状态为502，但是下次访问节点时会继续探测正确节点，直到找到正确的为止。