/**
 * Created by jie.huang on 2015/3/5.
 */
var REST_API = "";

var category = {
    1: "普通网页",
    2: "资源"
};

var jobStatus = {
    0: "失效",
    1: "生效"
};
var paramType ={
    0:"字符串",
    1:"YAML配置",
    2:"JSON配置"
    //2:"Groovy脚本"
};

var scriptType={
    1:"任务构建",
    2:"任务爬取"
};
var menus = [
    {path:"/monitor/system",text:"System",icon:"fa fa-paw"},
    {path:"/config/job",text:"任务管理",icon:"fa fa-bug"},
    {path:"/config/script",text:"预定义脚本",icon:"fa fa-google"},
    {isSplit:true,style:"border-bottom: thin dotted #f60;"},
    {path:"/monitor/task",text:"任务监控",icon:"fa fa-dashboard"},
    {path:"/monitor/client_sys_error",text:"客户端异常",icon:"fa fa-pie-chart"},
    {path:"/monitor/message",text:"消息监控",icon:"fa fa-send"},
    {path:"/monitor/proxy",text:"代理监控",icon:"fa fa-plug"},
    {isSplit:true,style:"border-bottom: thin dotted #f60;"},
    {path:"/help",text:" 帮助",icon:"fa fa-question-circle"},
    {path:"/version",text:"版本更新",icon:"fa fa-code-fork"},
    {path:"/about",text:"联系我们",icon:"fa fa-envelope"}
];
