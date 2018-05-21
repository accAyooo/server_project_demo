define(function(){
	var core = {};
	var root = this;
	var $window = $(window);
	
	core.win = $window;
	core.body = null;
	$(function(){
		core.body = $('body');
	});

	core._debug = false;
	
	/**
	 * @deprecated
	 */
	core._imghost = 'http://img.biubiu.heiyanimg.com';
	
	core.IMG_SERVER = core._imghost;
	core.static_path = core.IMG_SERVER + '/_res';
	core._imgfield = core.IMG_SERVER+'/_res/css/img/field.gif';
	
	core.Domain = function() {
		return "http://m.biubiu.ruochu.com";
	}();
    core.ajaxDomain = function() {
        var domain ='http://a.biubiu.ruochu.com/m' ;
        return domain;
    }();
    core.accountsDomain = function() {
        var domain ='http://accounts.biubiu.ruochu.com/m' ;
        return domain;
    }();
	core.setDefaultIcon = function( arrays, prop ) {
		var group = core.group;
		if(group > 0 ) {
			for(r in arrays) {
				if(prop) {
					arrays[r][prop].iconUrlSmall = arrays[r][prop].iconUrlSmall.replace(core.DEFAULT_IMAGE[0], core.DEFAULT_IMAGE[group])
				} else {
					arrays[r].iconUrlSmall = arrays[r].iconUrlSmall.replace(core.DEFAULT_IMAGE[0], core.DEFAULT_IMAGE[group])
				}
			}
		}
	},
	core.browser = function(){
		
		var ie = ("ActiveXObject" in window), 
			ie6 = ie && !window.XMLHttpRequest,
			ie7 = ie && window.XMLHttpRequest && !document.documentMode,
			ie8 = ie && !-[1,] && document.documentMode;
		
		return {
			ie: ie,
			ie6: ie6,
			ie7: ie7,
			ie8: ie8,
			oldie: ie6 || ie7 || ie8
		}
	}();
	
	core.Class = core.Class = function(){
        var initializing = false, fnTest = /xyz/.test(function(){xyz;}) ? /\b_super\b/ : /.*/;

        // The base Class implementation (does nothing)
        var Class = function(){};

        // Create a new Class that inherits from this class
        Class.extend = function(prop, statics) {
            var _super = this.prototype;

            // Instantiate a base class (but only create the instance,
            // don't run the init constructor)
            initializing = true;
            var prototype = new this();
            initializing = false;

            // Copy the properties over onto the new prototype
            for (var name in prop) {
                // Check if we're overwriting an existing function
                prototype[name] = typeof prop[name] == "function" &&
                    typeof _super[name] == "function" && fnTest.test(prop[name]) ?
                    (function(name, fn){
                        return function() {
                            var tmp = this._super;

                            // Add a new ._super() method that is the same method
                            // but on the super-class
                            this._super = _super[name];

                            // The method only need to be bound temporarily, so we
                            // remove it when we're done executing
                            var ret = fn.apply(this, arguments);
                            this._super = tmp;

                            return ret;
                        };
                    })(name, prop[name]) :
                    prop[name];
            }

            // The dummy class constructor
            function Class() {
                // All construction is actually done in the init method
                if ( !initializing && this.init )
                    this.init.apply(this, arguments);
            }

            // Populate our constructed prototype object
            Class.prototype = prototype;

            // Enforce the constructor to be what we expect
            Class.prototype.constructor = Class;

            // And make this class extendable
            Class.extend = arguments.callee;

            for(var i in statics){
                Class[i] = statics[i];
            }
            
            if( Class.init ){
            	Class.init.call(Class);
            }

            return Class;
        };

        return Class;
    }();
	
	core.Service = function(member){
		if( typeof member !== 'function' ) return null;
		
		return core.Class.extend(member());
	};
	
	core.extend = $.extend;
	
	core.url = {
		img: {
			upload: '/ajax/image/temp/add',
			del: '/ajax/image/del'
		}
	};
	
	core.fn = Function;

	core.error = [];
	core.register = function(e, c) {
		var g = e.split(".");
		var f = core;
		var b = core.error;
		var d = null;
		while (d = g.shift()) {
			if (g.length) {
				if (f[d] === undefined) {
					f[d] = {}
				}
				f = f[d]
			} else {
				if (f[d] === undefined) {
					if( typeof c === 'string' && c === 'check' ){
						core.console.log('Property undefined:'+e);
						return $.noop;
					}
					try {
						f[d] = c(core);
					} catch(h) {
						b.push(h)
					}
				}else{
					if( typeof c == 'string' && c == 'check' ){
						return f[d]
					}
					core.console.log('redefined:'+e)
				}
			}
		}
	};
	
	core.getMethod = function(e){
		return core.register( e, 'check')
	};
	
	//debug
	core.console = {
		log: function(){
			try{
				console.log(arguments)
			}catch(e){

			}
		},
		dir: function(){
			try{
				console.dir(arguments)
			}catch(e){

			}
		}
	};
	
	//延迟函数
	core.delay = function(fn, delay){
		return setTimeout(fn, delay || 0);
	};
	
	//actions的自定义回调
	core.hasEvent = function(selector, name){
		if(!name) return false;
		var name = name.match(/(^[^\.]*)(\.(.*))?/),
			type = name[1], namespace = name[3], method;
			events = $._data($(selector)[0]).events;
			
		if(type in events && (method = events[type])){
			if(typeof namespace === 'undefined'){
				return true;
			}
			for(var m in method){
				if(method[m].namespace == namespace){
					return true;
				}
			}
		}
		return false;
	};
	
	//随机
	core.random = function(target, start){
		if( typeof target == 'number' ){
			var count = target || 5;
			start = start || 1;
			return Math.round( Math.random() * Math.pow( 10,count ) ) * start;
		}
		if( (typeof target == 'object' || typeof target == 'string') && target.hasOwnProperty('length') ){
			var isString = typeof target == 'string';
			if( isString ){
				target = target.split('');
			}
			var obj = [], ex = {}, max = target.length || start || 10;
		    var get = function(){
		        var key = Math.round( Math.random()*max );
		        return key in ex || key == max ? get() : ( ex[key] = true ) && key;
		    };
		    if(target.length == 0){
		    	for(var i = 0; i < max; i++){
			        obj[i] = get();
			    }
		    }else{
		    	for(var i = 0; i < max; i++){
			    	obj.push(target[get()])
			    }
		    }
		    if( isString ){
		    	return obj.join('');
		    }
		    return obj;
		}
	};
	
	//使用平台
	core.client = {
		init: function () {
			this.browser = this.searchString(this.dataBrowser) || "An unknown browser";
			this.version = this.searchVersion(navigator.userAgent)
				|| this.searchVersion(navigator.appVersion)
				|| "an unknown version";
			this.OS = this.searchString(this.dataOS) || "an unknown OS";
		},
		searchString: function (data) {
			for (var i=0;i<data.length;i++)	{
				var dataString = data[i].string;
				var dataProp = data[i].prop;
				this.versionSearchString = data[i].versionSearch || data[i].identity;
				if (dataString) {
					if (dataString.indexOf(data[i].subString) != -1)
						return data[i].identity;
				}
				else if (dataProp)
					return data[i].identity;
			}
		},
		searchVersion: function (dataString) {
			var index = dataString.indexOf(this.versionSearchString);
			if (index == -1) return;
			return parseFloat(dataString.substring(index+this.versionSearchString.length+1));
		},
		dataBrowser: [
			{
				string: navigator.userAgent,
				subString: "Chrome",
				identity: "Chrome"
			},
			{ 	string: navigator.userAgent,
				subString: "OmniWeb",
				versionSearch: "OmniWeb/",
				identity: "OmniWeb"
			},
			{
				string: navigator.vendor,
				subString: "Apple",
				identity: "Safari",
				versionSearch: "Version"
			},
			{
				prop: window.opera,
				identity: "Opera"
			},
			{
				string: navigator.vendor,
				subString: "iCab",
				identity: "iCab"
			},
			{
				string: navigator.vendor,
				subString: "KDE",
				identity: "Konqueror"
			},
			{
				string: navigator.userAgent,
				subString: "Firefox",
				identity: "Firefox"
			},
			{
				string: navigator.vendor,
				subString: "Camino",
				identity: "Camino"
			},
			{		// for newer Netscapes (6+)
				string: navigator.userAgent,
				subString: "Netscape",
				identity: "Netscape"
			},
			{
				string: navigator.userAgent,
				subString: "MSIE",
				identity: "Explorer",
				versionSearch: "MSIE"
			},
			{
				string: navigator.userAgent,
				subString: "Gecko",
				identity: "Mozilla",
				versionSearch: "rv"
			},
			{ 		// for older Netscapes (4-)
				string: navigator.userAgent,
				subString: "Mozilla",
				identity: "Netscape",
				versionSearch: "Mozilla"
			}
		],
		dataOS : [
			{
				string: navigator.userAgent,
				subString: "iPad",
				identity: "iPad"
			},
			{
				string: navigator.platform,
				subString: "Win",
				identity: "Windows"
			},
			{
				string: navigator.platform,
				subString: "Mac",
				identity: "Mac"
			},
			
			{
				string: navigator.userAgent,
				subString: "Android",
				identity: "Android"
			},
			{
				string: navigator.userAgent,
				subString: "iPhone",
				identity: "iPhone/iPod"
		    },
			{
				string: navigator.platform,
				subString: "Linux",
				identity: "Linux"
			}
		]
	};
	core.client.init();
	
	/**
	 * like python range
	 * TODO bug here
	 */
	core.range = function(/*number*/ start, /*number*/ end, /*number -1 or > 0*/ step ){
		step = step == (-1 || step > 0) ? step : 1;
		if(arguments.length == 1){
			end = start;
			start = 0;
		}
		var arr = [], isReverse = step === -1;
		for(var i = start, step = isReverse ? 1 : step; i < end; i+=step){
			arr.push(i)
		}
		return isReverse ? arr.reverse() : arr;
	};
	
	/*!
	 * jQuery Cookie Plugin v1.3.1
	 * https://github.com/carhartl/jquery-cookie
	 *
	 * Copyright 2013 Klaus Hartl
	 * Released under the MIT license
	 */
	core.cookie = function(){
		var pluses = /\+/g;

		function raw(s) {
			return s;
		}

		function decoded(s) {
			return decodeURIComponent(s.replace(pluses, ' '));
		}

		function converted(s) {
			if (s.indexOf('"') === 0) {
				// This is a quoted cookie as according to RFC2068, unescape
				s = s.slice(1, -1).replace(/\\"/g, '"').replace(/\\\\/g, '\\');
			}
			try {
				return config.json ? JSON.parse(s) : s;
			} catch(er) {}
		}

		var config = cookie = function (key, value, options) {

			// write
			if (value !== undefined) {
				options = $.extend({}, config.defaults, options);

				if (typeof options.expires === 'number') {
					var days = options.expires, t = options.expires = new Date();
					t.setDate(t.getDate() + days);
				}

				value = config.json ? JSON.stringify(value) : String(value);

				return (document.cookie = [
					encodeURIComponent(key), '=', config.raw ? value : encodeURIComponent(value),
					options.expires ? '; expires=' + options.expires.toUTCString() : '', // use expires attribute, max-age is not supported by IE
					options.path    ? '; path=' + options.path : '',
					options.domain  ? '; domain=' + options.domain : '',
					options.secure  ? '; secure' : ''
				].join(''));
			}

			// read
			var decode = config.raw ? raw : decoded;
			var cookies = document.cookie.split('; ');
			var result = key ? undefined : {};
			for (var i = 0, l = cookies.length; i < l; i++) {
				var parts = cookies[i].split('=');
				var name = decode(parts.shift());
				var cookie = decode(parts.join('='));

				if (key && key === name) {
					result = converted(cookie);
					break;
				}

				if (!key) {
					result[name] = converted(cookie);
				}
			}

			return result;
		};
		
		config.defaults = {};
		
		var removeCookie = function (key, options) {
			if (cookie(key) !== undefined) {
				cookie(key, '', $.extend(options, { expires: -1 }));
				return true;
			}
			return false;
		};
		
		cookie.removeCookie = removeCookie;
		
		return cookie
	}();
	
	//是否为json格式数据
	core.isJSON = function(str){
		if( typeof str === 'object' && str instanceof Object ){
			return str;
		}else if( typeof str === 'string' && /^\{(.*)\}$/.test($.trim(str)) ){
			try{
				str = $.parseJSON(str);
			}catch(e){
				core.console.log(str,'is not json data');
				return false;
			}
			return str;
		}
		return false;
	};
	
	/**
	 * 请求是否被拦截
	 * @param {String, String, Function} html, url, callback 检测字符串，检测地址，回调函数
	 */
	core.checkRequestRob = function( html, url, callback ){
		var match = null;
		if( arguments.length == 2){
			callback = url;
			url = '';
		}
		if( !core.isJSON(html) && ( /<html>|<\/html>|<iframe>|<\/iframe>/i.test(html) || html.indexOf(url) != -1 ) ){
			var src = html.match(/\d+\.\d+\.\d+\.\d+/);
			core.console.log('请求被拦劫，拦劫代理IP：'+src);
			(callback || $.noop)();
			return true;
		}
		return false;
	};
	
	//伪ajax回调测试
	core.ajax = function(options){
		if( options.debug ){
			var deferred = new $.Deferred();
			var code = options.statusCode || 200;
			var delay = options.delay || 300;
			switch(code){
				case 200:
					core.delay(function(){
						deferred.resolve({
							code: 1
						});
					}, delay);
					break;
				case 403:
				case 404:
				case 500:
					core.delay(function(){
						deferred.reject({
							code: 0
						});
					}, delay);
					break;
			}
			return deferred;
		}else{
			return $.ajax(options);
		}
	};
    
    core.fileSize = function( size ){
        return {
             M: Math.round(size/1024/1024),
            KB: Math.round(size/1024)
        }
    };
    
    core.cap = function(obj){
    	if( ( typeof obj == 'object' || typeof obj == 'string') && obj.hasOwnProperty('length') ) return obj.length;
    	if( typeof obj == 'object' ){
    		var c = 0;
    		for(var i in obj ) c++;
    		return c;
    	}
    };
    
    core.lazyLoad = function(options){
		options = $.extend({
			context : null,
			height : 0
		}, options);
		
		var win = core.win;
		var context = $(options.context);
		if(!context.length) return;
		var pageTop = function() {
			return document.documentElement.clientHeight
			 	   + Math.max(document.documentElement.scrollTop, document.body.scrollTop)
				   - options.height;
		};
		var imgLoad = function() {
			context.find('img[orgSrc]').each(function() {
				if($(this).offset().top <= pageTop() && $(this).is(':visible') ){
					var orgSrc = this.getAttribute('orgSrc');
					this.setAttribute('src', orgSrc);
					this.removeAttribute('orgSrc');
				}
			});
		};
		win.bind('lazyload', imgLoad);
		core.xresize('scroll.lazyload',{
			after: imgLoad
		});
	};
	

	//图像加载
	core.loadImage = function(url, callback){
	 var img = new Image();
	    img.src = url;
	    if (img.complete) {
	    	callback.call(img);
	    }
	    else {
	        img.onload = function(){
	        	callback.call(img);
	        };
	        img.src = img.src
        }
    };

	/**
	 * HTML5 upload
	 * @param url
	 * @param data
	 * @param options
	 * @returns {XMLHttpRequest} 2.0
	 */
	core.upload = function(url, data, options){
        options = $.extend({
            progress    : $.noop,
            load        : $.noop,
            error       : $.noop,
            abort       : $.noop
        }, options);

        var fd = new FormData();
        $.each(data, function( key, value ){
            fd.append(key, value);
        });

        var xhr = new XMLHttpRequest();

        xhr.addEventListener('load', function( e ){
            options.load.call(e, e.currentTarget.responseText);
        }, false);

        xhr.addEventListener('error', options.error, false);

        xhr.upload.addEventListener('progress', function(e){
            options.progress.call( e, Math.ceil((e.loaded / e.total) * 100), xhr );
        }, false);

        xhr.addEventListener('abort', options.abort, false);

        xhr.open('post', url, true);

        xhr.send(fd);

        return xhr;
    };
	
	core.hover = function(handle, float, toggleClass){
	    var timer = null, showTimer = null;

	    function clear(){
	        if( timer ){
	            clearTimeout(timer);
	            timer = null;
	        }
	        if( showTimer ){
	            clearTimeout(showTimer);
	            showTimer = null;
	        }
	    }

	    function hide(){
	        clear();
	        timer = core.delay(function(){
        		toggleClass ? float.addClass('hidden') : float.hide();
	        }, 30)
	    }
	    
	    function show(){
	    	toggleClass ? float.removeClass('hidden') : float.show();
	    }
	    
	    handle.hover(function(){
	        clear();
	        show();
	    }, function(){
	        hide();
	    });

	    float.hover(function(){
	        clear();
	        show();
	    }, function(){
	        hide();
	    })
	};
	
	core.whenSeenElement = function(name, ele, callback){
		var name = 'resize.'+name+' scroll.'+name;
		core.xresize(name, function( e ){
			if( core.win.scrollTop()+core.win.height() < ele.offset().top ) return;
			core.xresize.unbind(name);
			(callback || $.noop)();
		})
	};
	
	/**
	 * 固定布局
	 * @param {jQuery object, object}  ele, options
	 */
	core.positionFixed = function(){
		var id = 0, abs = Math.abs, ceil = Math.ceil;
		
		function getPosition(ele, options){
			var ew = ele.outerWidth(), eh = ele.outerHeight(),
				ww = core.win.width(), wh = core.win.height(), st = core.win.scrollTop();
				
			return {
				left: getLeft(ew, ww, options),
				top: getTop(eh, wh, st, options)
			}
		}
		
		function getLeft(ew, ww, options){
			if( options.centerHorizontal ){
				return ceil( abs(ww-ew)/2 );
			}
			if( options.offset.left ){
				return options.offset.left;
			}
			if( options.offset.right ){
				return ww - options.offset.right - ew;
			}
			return 0;
		}
		
		function getTop(eh, wh, st, options){
			if( options.centerVertical ){
				return st + ceil( abs(wh-eh)/2 );
			}
			if( options.offset.top ){
				return st + options.offset.top;
			}
			if( options.offset.bottom ){
				return st + wh - eh - options.offset.bottom;
			}
			return 0;
		}
		
		return function(ele, options){
			id++;
			var name = 'scroll.positionFixed' + id +' resize.positionFixed' + id;
			
			ele = ele.jquery ? ele : $(ele);
			
			var defaultValue = core.browser.ie ? 0 : 'auto', firstRun = false;
			
			options = $.extend({
				offset: {
					left: defaultValue,
					right: defaultValue,
					top: defaultValue,
					bottom: defaultValue
				},
				centerHorizontal: false,
				centerVertical: false,
				onPosition: $.noop,
				after: $.noop,
				before: $.noop
			}, options);
			
			if( core.browser.ie6 ){
				ele.css({
					position: 'absolute'
				});
				core.xresize(name, {
					before: options.before,
					after: function(e){
						ele.css(getPosition(ele, options));
						options.after.call(core.win, e);
						if( !firstRun ){
							firstRun = true;
							options.onPosition(ele);
						}
					}
				});
			}else{
				ele.css(options.offset);
				if( options.centerHorizontal ){
					ele.css({
						'left': '50%',
						'marginLeft': -ele.outerWidth()/2
					});
				}
				if( options.centerVertial ){
					ele.css({
						'top': '50%',
						'marginTop': -ele.outerHeight()/2
					});
				}
				options.onPosition(ele);
			}
			
			return name;
		}
	}();
	
	/**
	 * 除了内容与触发手柄，点击其他位置都隐藏
	 * @param {jQuery Object, jQuery Object}
	 */
	core.clickAnyWhereHideButMe = function(){
		var pending = 0;
		
		return function(element, handle, callback, autoUnbind){
			var ele = element && element.jquery ? element[0] : element,
				h = handle && handle.jquery ? handle[0] : handle,
				autoUnbind = 'undefined' == typeof autoUnbind ? true : autoUnbind,
				name = 'mousedown.cawhb'+pending++;
				
			if( !ele && !h ){
				return;
			}
				
			callback = callback || function(e, element){
				element.hide();
			};
			
			$(document).bind(name, function(e){
				if((h ? e.target != h : true) && e.target != ele && !$.contains(ele, e.target)){
					callback(e, element);
				}
			});
			
			return name;
		}
	}();
	
	core.unbindDocumentEvent = function(name){
		$(document).unbind(name);
	};
	
	/**
	 * 修复当滚动和重置窗口大小时多次调用resize, scroll事件的bug
	 * @param {[string, function], [object, function]} 事件名，配置项
	 */
	core.xresize = function(name, options){
		var timer = null, isEnd = false;

		var setting = {
			//一绑定便执行
			init: true,
			//滚动停止后调用
			after: $.noop,
			//开始滚动时调用
			before: $.noop,
			delay: 100	
		};
		
		if(arguments.length == 1){
			setting.after = name;
			name = 'scroll resize';
		}else{
			name = name || 'scroll resize';
			if( typeof options == 'function'){
				setting.after = options;
			}else{
				setting = $.extend(setting, options);
			}
		}
		
		core.win.bind(name, function(e){
			if(timer){
				clearTimeout(timer);
				timer = null;
			}
			if(isEnd == false){
				isEnd = true;
				setting.before.call(core.win, e)
			}
			timer = core.delay(function(){
				isEnd = false;
				setting.after.call(core.win, e);
			}, setting.delay)
		});
		
		if(setting.init){
			core.win.trigger(name.split(' ')[0]);
		}
		
		return core.win;
	};
	
	core.xresize.unbind = function(name){
		core.win.unbind(name);
	};
	/**
	 * 判断是否是微信登录
	 */
	core.isAuth = function(){
		function GetRequest() { 
			var url = location.search; //获取url中"?"符后的字串 
			var theRequest = new Object(); 
			if (url.indexOf("?") != -1) { 
			var str = url.substr(1); 
			strs = str.split("&"); 
			for(var i = 0; i < strs.length; i ++) { 
				theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]); 
			} 
			} 
			return theRequest; 
		}
		var cutUrl = new GetRequest();
		if(typeof cutUrl == "object" && cutUrl instanceof Object ){
			if(cutUrl.isAuth == undefined ){
				return;
			}
			if(cutUrl.isAuth == '1'){
				core.cookie("biubiu_wx-auth", true, {expires: 1, path: '/', domain: "biubiu.ruochu.com"});
			}
		}
	};
	/**
	 * 判断是否是微信浏览器
	 */
	core.Weixin = function(){
		var weixin = false;
		var ua = navigator.userAgent.toLowerCase();
		if (ua.match(/MicroMessenger/i) == 'micromessenger') {
			weixin =  true;
		}
		return weixin;
	}();
	core.go = function( url, isNewWindow ){
		var local = location, href = '';
		if( url == 'me'){
			href = local.href;
		}else if( /^#/.test(url) ){
			href = local.origin + local.pathname + url;
		}else{
			href = url;
		}
		if( !isNewWindow ){
			local.href = href;
		}else{
			window.open(href)
		}
		return local;
	};
	
	core.useAnim = function(use, notuse){
		return !core.browser.ie6;
	}();

	core.alertMessage = function(second) {
		second = second ? (second * 1000) : 1500;
		var timer = null;

		return function(mes) {
			if ($(".message-alert").length === 0) {
				$("body").append("<div class='message-alert'></div>")
			}
			clearTimeout(timer);
			$(".message-alert").html(mes).show();
			timer = setTimeout(function() {
				$(".message-alert").hide();
			}, second)
		}
	}
	
	/**
	 * 判断是否是微信浏览器
	 */
	core.Weixin = function(){
		var weixin = false;
		var ua = navigator.userAgent.toLowerCase();
		if (ua.match(/MicroMessenger/i) == 'micromessenger') {
			weixin =  true;
		}
		return weixin;
	}();
	
	core.rsaKey = function(){
        return "90729ae6fb1d29682234cdaaf937f989100d32cf8ab678b3f838df95c3b200c3731b4308367404c4bba75fb41b8804e5118325d9311f7d715d7370400d8cef1845adb29e3b07db046765ff596d49b75c5eb7e010a869e4d9e283e9e128e9e649ee6e39db13db738bd9ac73acc984d700149520e3769e09aa5be40116113ccbe3"
    }();
	// 对Date的扩展，将 Date 转化为指定格式的String 
	// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
	// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
	// 例子： 
	// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 
	// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18 
	Date.prototype.Format = function(fmt) 
	{ //author: meizz 
	  var o = { 
	    "M+" : this.getMonth()+1,                 //月份 
	    "d+" : this.getDate(),                    //日 
	    "H+" : this.getHours(),                   //小时 
	    "m+" : this.getMinutes(),                 //分 
	    "s+" : this.getSeconds(),                 //秒 
	    "q+" : Math.floor((this.getMonth()+3)/3), //季度 
	    "S"  : this.getMilliseconds()             //毫秒 
	  }; 
	  if(/(y+)/.test(fmt)) 
	    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
	  for(var k in o) 
	    if(new RegExp("("+ k +")").test(fmt)) 
	  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length))); 
	  return fmt; 
	}
	
	return core
});
