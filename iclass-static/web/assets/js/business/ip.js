/**
 * Created by yang.tang on 2017/2/15.
 */
    //request url
    //var ip = "localhost";
    //var ip = "123.206.89.164";
	/**
	 * 获取URL
	 * @type {string}
	 */
	var url = document.URL;
	var domain = url.match(/http[s]?:\/\/(.*?)([:\/]|$)/);

    var user_port = "52020";
    var ph_port = "52021";
    var posfix = "/iclass";
    var user_url   = domain[0] + user_port + posfix;
    var ppt_hw_url = domain[0] + ph_port + posfix;
