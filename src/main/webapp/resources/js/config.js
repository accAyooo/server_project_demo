var BOWER_PATH = "./components";

requirejs.config({
    paths: {
        jquery 		: BOWER_PATH + '/jquery/dist/jquery',
        kernel 		: BOWER_PATH + '/core/kernel',
        dot			: BOWER_PATH + '/dot/doT.min',
    }
});

require(['init', 'jquery']);

