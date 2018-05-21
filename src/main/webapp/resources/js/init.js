define(["kernel"],function(core){

	if(_inlineCodes && _inlineCodes.length){
		$.map(_inlineCodes, function(fn){
			typeof fn === 'function' && fn()
		})
	}
});