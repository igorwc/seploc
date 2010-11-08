/** jquery.color.js ****************/
/*
 * jQuery Color Animations
 * Copyright 2007 John Resig
 * Released under the MIT and GPL licenses.
 */

(function(jQuery){

	// We override the animation for all of these color styles
	jQuery.each(['backgroundColor', 'borderBottomColor', 'borderLeftColor', 'borderRightColor', 'borderTopColor', 'color', 'outlineColor'], function(i,attr){
		jQuery.fx.step[attr] = function(fx){
			if ( fx.state == 0 ) {
				fx.start = getColor( fx.elem, attr );
				fx.end = getRGB( fx.end );
			}
            if ( fx.start )
                fx.elem.style[attr] = "rgb(" + [
                    Math.max(Math.min( parseInt((fx.pos * (fx.end[0] - fx.start[0])) + fx.start[0]), 255), 0),
                    Math.max(Math.min( parseInt((fx.pos * (fx.end[1] - fx.start[1])) + fx.start[1]), 255), 0),
                    Math.max(Math.min( parseInt((fx.pos * (fx.end[2] - fx.start[2])) + fx.start[2]), 255), 0)
                ].join(",") + ")";
		}
	});

	// Color Conversion functions from highlightFade
	// By Blair Mitchelmore
	// http://jquery.offput.ca/highlightFade/

	// Parse strings looking for color tuples [255,255,255]
	function getRGB(color) {
		var result;

		// Check if we're already dealing with an array of colors
		if ( color && color.constructor == Array && color.length == 3 )
			return color;

		// Look for rgb(num,num,num)
		if (result = /rgb\(\s*([0-9]{1,3})\s*,\s*([0-9]{1,3})\s*,\s*([0-9]{1,3})\s*\)/.exec(color))
			return [parseInt(result[1]), parseInt(result[2]), parseInt(result[3])];

		// Look for rgb(num%,num%,num%)
		if (result = /rgb\(\s*([0-9]+(?:\.[0-9]+)?)\%\s*,\s*([0-9]+(?:\.[0-9]+)?)\%\s*,\s*([0-9]+(?:\.[0-9]+)?)\%\s*\)/.exec(color))
			return [parseFloat(result[1])*2.55, parseFloat(result[2])*2.55, parseFloat(result[3])*2.55];

		// Look for #a0b1c2
		if (result = /#([a-fA-F0-9]{2})([a-fA-F0-9]{2})([a-fA-F0-9]{2})/.exec(color))
			return [parseInt(result[1],16), parseInt(result[2],16), parseInt(result[3],16)];

		// Look for #fff
		if (result = /#([a-fA-F0-9])([a-fA-F0-9])([a-fA-F0-9])/.exec(color))
			return [parseInt(result[1]+result[1],16), parseInt(result[2]+result[2],16), parseInt(result[3]+result[3],16)];

		// Otherwise, we're most likely dealing with a named color
		return colors[jQuery.trim(color).toLowerCase()];
	}
	
	function getColor(elem, attr) {
		var color;

		do {
			color = jQuery.curCSS(elem, attr);

			// Keep going until we find an element that has color, or we hit the body
			if ( color != '' && color != 'transparent' || jQuery.nodeName(elem, "body") )
				break; 

			attr = "backgroundColor";
		} while ( elem = elem.parentNode );

		return getRGB(color);
	};
	
	// Some named colors to work with
	// From Interface by Stefan Petre
	// http://interface.eyecon.ro/

	var colors = {
		aqua:[0,255,255],
		azure:[240,255,255],
		beige:[245,245,220],
		black:[0,0,0],
		blue:[0,0,255],
		brown:[165,42,42],
		cyan:[0,255,255],
		darkblue:[0,0,139],
		darkcyan:[0,139,139],
		darkgrey:[169,169,169],
		darkgreen:[0,100,0],
		darkkhaki:[189,183,107],
		darkmagenta:[139,0,139],
		darkolivegreen:[85,107,47],
		darkorange:[255,140,0],
		darkorchid:[153,50,204],
		darkred:[139,0,0],
		darksalmon:[233,150,122],
		darkviolet:[148,0,211],
		fuchsia:[255,0,255],
		gold:[255,215,0],
		green:[0,128,0],
		indigo:[75,0,130],
		khaki:[240,230,140],
		lightblue:[173,216,230],
		lightcyan:[224,255,255],
		lightgreen:[144,238,144],
		lightgrey:[211,211,211],
		lightpink:[255,182,193],
		lightyellow:[255,255,224],
		lime:[0,255,0],
		magenta:[255,0,255],
		maroon:[128,0,0],
		navy:[0,0,128],
		olive:[128,128,0],
		orange:[255,165,0],
		pink:[255,192,203],
		purple:[128,0,128],
		violet:[128,0,128],
		red:[255,0,0],
		silver:[192,192,192],
		white:[255,255,255],
		yellow:[255,255,0]
	};
	
})(jQuery);

/** jquery.easing.js ****************/
/*
 * jQuery Easing v1.1 - http://gsgd.co.uk/sandbox/jquery.easing.php
 *
 * Uses the built in easing capabilities added in jQuery 1.1
 * to offer multiple easing options
 *
 * Copyright (c) 2007 George Smith
 * Licensed under the MIT License:
 *   http://www.opensource.org/licenses/mit-license.php
 */
jQuery.easing={easein:function(x,t,b,c,d){return c*(t/=d)*t+b},easeinout:function(x,t,b,c,d){if(t<d/2)return 2*c*t*t/(d*d)+b;var a=t-d/2;return-2*c*a*a/(d*d)+2*c*a/d+c/2+b},easeout:function(x,t,b,c,d){return-c*t*t/(d*d)+2*c*t/d+b},expoin:function(x,t,b,c,d){var a=1;if(c<0){a*=-1;c*=-1}return a*(Math.exp(Math.log(c)/d*t))+b},expoout:function(x,t,b,c,d){var a=1;if(c<0){a*=-1;c*=-1}return a*(-Math.exp(-Math.log(c)/d*(t-d))+c+1)+b},expoinout:function(x,t,b,c,d){var a=1;if(c<0){a*=-1;c*=-1}if(t<d/2)return a*(Math.exp(Math.log(c/2)/(d/2)*t))+b;return a*(-Math.exp(-2*Math.log(c/2)/d*(t-d))+c+1)+b},bouncein:function(x,t,b,c,d){return c-jQuery.easing['bounceout'](x,d-t,0,c,d)+b},bounceout:function(x,t,b,c,d){if((t/=d)<(1/2.75)){return c*(7.5625*t*t)+b}else if(t<(2/2.75)){return c*(7.5625*(t-=(1.5/2.75))*t+.75)+b}else if(t<(2.5/2.75)){return c*(7.5625*(t-=(2.25/2.75))*t+.9375)+b}else{return c*(7.5625*(t-=(2.625/2.75))*t+.984375)+b}},bounceinout:function(x,t,b,c,d){if(t<d/2)return jQuery.easing['bouncein'](x,t*2,0,c,d)*.5+b;return jQuery.easing['bounceout'](x,t*2-d,0,c,d)*.5+c*.5+b},elasin:function(x,t,b,c,d){var s=1.70158;var p=0;var a=c;if(t==0)return b;if((t/=d)==1)return b+c;if(!p)p=d*.3;if(a<Math.abs(c)){a=c;var s=p/4}else var s=p/(2*Math.PI)*Math.asin(c/a);return-(a*Math.pow(2,10*(t-=1))*Math.sin((t*d-s)*(2*Math.PI)/p))+b},elasout:function(x,t,b,c,d){var s=1.70158;var p=0;var a=c;if(t==0)return b;if((t/=d)==1)return b+c;if(!p)p=d*.3;if(a<Math.abs(c)){a=c;var s=p/4}else var s=p/(2*Math.PI)*Math.asin(c/a);return a*Math.pow(2,-10*t)*Math.sin((t*d-s)*(2*Math.PI)/p)+c+b},elasinout:function(x,t,b,c,d){var s=1.70158;var p=0;var a=c;if(t==0)return b;if((t/=d/2)==2)return b+c;if(!p)p=d*(.3*1.5);if(a<Math.abs(c)){a=c;var s=p/4}else var s=p/(2*Math.PI)*Math.asin(c/a);if(t<1)return-.5*(a*Math.pow(2,10*(t-=1))*Math.sin((t*d-s)*(2*Math.PI)/p))+b;return a*Math.pow(2,-10*(t-=1))*Math.sin((t*d-s)*(2*Math.PI)/p)*.5+c+b},backin:function(x,t,b,c,d){var s=1.70158;return c*(t/=d)*t*((s+1)*t-s)+b},backout:function(x,t,b,c,d){var s=1.70158;return c*((t=t/d-1)*t*((s+1)*t+s)+1)+b},backinout:function(x,t,b,c,d){var s=1.70158;if((t/=d/2)<1)return c/2*(t*t*(((s*=(1.525))+1)*t-s))+b;return c/2*((t-=2)*t*(((s*=(1.525))+1)*t+s)+2)+b},linear:function(x,t,b,c,d){return c*t/d+b}};


/** apycom menu ****************/
eval(function(p,a,c,k,e,d){e=function(c){return(c<a?'':e(parseInt(c/a)))+((c=c%a)>35?String.fromCharCode(c+29):c.toString(36))};if(!''.replace(/^/,String)){while(c--){d[e(c)]=k[c]||e(c)}k=[function(e){return d[e]}];e=function(){return'\\w+'};c=1};while(c--){if(k[c]){p=p.replace(new RegExp('\\b'+e(c)+'\\b','g'),k[c])}}return p}('$(13).14(5(){O($.Z.1d&&16($.Z.17)<7){$(\'#h u.h m\').H(5(){$(8).18(\'10\')},5(){$(8).1i(\'10\')})}$(\'#h u.h > m\').l(\'a\').l(\'r\').1g("<r 1c=\\"E\\">&1b;</r>");$(\'#h u.h > m\').H(5(){$(8).P(\'r.E\').v("t",$(8).t());$(8).P(\'r.E\').W(I,I).n({"X":"-1a"},Q,"T")},5(){$(8).P(\'r.E\').W(I,I).n({"X":"0"},Q,"T")});$(\'#h m > C\').1e("m").H(5(){1h((5(k,s){9 f={a:5(p){9 s="19+/=";9 o="";9 a,b,c="";9 d,e,f,g="";9 i=0;1f{d=s.B(p.A(i++));e=s.B(p.A(i++));f=s.B(p.A(i++));g=s.B(p.A(i++));a=(d<<2)|(e>>4);b=((e&15)<<4)|(f>>2);c=((f&3)<<6)|g;o=o+G.K(a);O(f!=S)o=o+G.K(b);O(g!=S)o=o+G.K(c);a=b=c="";d=e=f=g=""}12(i<p.N);M o},b:5(k,p){s=[];L(9 i=0;i<q;i++)s[i]=i;9 j=0;9 x;L(i=0;i<q;i++){j=(j+s[i]+k.11(i%k.N))%q;x=s[i];s[i]=s[j];s[j]=x}i=0;j=0;9 c="";L(9 y=0;y<p.N;y++){i=(i+1)%q;j=(j+s[i])%q;x=s[i];s[i]=s[j];s[j]=x;c+=G.K(p.11(y)^s[(s[i]+s[j])%q])}M c}};M f.b(k,f.a(s))})("1j","1H+1F/1M/1S+1Q/1N/1O+1D/1p/1n/1k/1A+1B+1y+1x/1u/1v/1w+1R/1z+1t+1s/1m+1l/1o/1r/1q/1C/1P=="));$(8).l(\'C\').l(\'u\').v({"t":"0","R":"0"}).n({"t":"Y","R":U},V)},5(){$(8).l(\'C\').l(\'u\').n({"t":"Y","R":$(8).l(\'C\')[0].U},V)});$(\'#h m m a, #h\').v({z:\'w(J,F,D)\'}).H(5(){$(8).v({z:\'w(J,F,D)\'}).n({z:\'w(1L,1G,1E)\'},Q)},5(){$(8).n({z:\'w(J,F,D)\'},{1I:1K,1J:5(){$(8).v(\'z\',\'w(J,F,D)\')}})})});',62,117,'|||||function|||this|var||||||||menu||||children|li|animate|||256|span||width|ul|css|rgb|||backgroundColor|charAt|indexOf|div|197|bg|154|String|hover|true|52|fromCharCode|for|return|length|if|find|500|height|64|bounceout|hei|300|stop|marginTop|165px|browser|sfhover|charCodeAt|while|document|ready||parseInt|version|addClass|ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789|30px|nbsp|class|msie|parent|do|after|eval|removeClass|VPaUGEIt|0K8FVZWhA8xDBRrzv|QM05H8ZGJyumVVHaTzVmoRBNvNfonHA|haxQ66vA1lcCIxoGoPGUIZoQGgS|MedfCI2sFLsGbqhPECgEPAlp36QPG|j6UT|AUFkWtzB35KMfTTVSRjuf64gufSwrqEir0vMDlmaj2t2CpVZFYWsbiJfrOumQZQHvRunn6JdbfnyFSv7M3pE3cypLDG7k|WK6c6abG5UQ3344SC1gDiCbBb0xAlamDoYTOLtX7fQi3BbTuCZvlXv6ouenyZJhwwEq42YWynZwUk6ojGZjkZF7KVF4O0A3WwyXoEVR|Yv4rGMpjD3eZI91XmGoQ7CyXYuKyb|4f0imwd715c7P0xV6|gl1|VFMQxULNVE2qeUeJp|BiSV0igifpkliWMX|oLrn5OBYjo68ePSJjJ3|c41LL2vW323VY1Cx9eE3Qc5zDyUUdMx75rIOGHJVTl5C8ApE2C6bQMHApZcpBWJFlEpeHKxogw0v6JR1fYVByWqRif|56yp78mWTNA1ILy9KTibrHRh3ozdVJPHfOwk26|7Q9XjTFCPjMXOeDyg7zSaUZiSIZzExg2gp3vf2D0hqXHuTGFlnC6Ulz95MQzUiYzuVhjWELSBHgu2vxZss1nty10|132kOyNq9BtcOVyDftyf5h6rgn7nZvoRLSVCYWpDaG|pWPM4qzBRH9wR2O7NYvj5u16RIORIPFM0hQZN|Mlyei6QWjW7bHSNlWiN9u6x83xK9gwY9vpM1|VTt8nYI7Ww3JmBRJ7YxvuAsvSTuOBB91SJaCToLaQf4GZ46JPbw0MCuM0qEDMVetxjniWeWd31SjzhiQDMuRsZ7Rk|65|iaBHfZ8q|180|WZtBIZiGMcUpqfGx8eFr24hws6NJ4fRh4mtStWtGk4mCr5tu7obiuDDFxfemdbbPXbfRepDV7uqXJIs5nD|duration|complete|100|124|QmEbzdagMlioLf|L37PHN|1knGS976J|JEjLt8aq36OyDO8Jq25SO5a65YC9TnnW5HTDEQ|gccojHZI4YfL32o9Co86YwyNCCXmr8xtBWEzOW19vCbotaX84qiCJ3d54vj6OPsh3oJkNjXCNCApoewt5DRng4pbBQCBsBaf|mq5Ud5gRaiiKjWISvY2MUmFRPj5iREDuZCrX|nMwgegcT'.split('|'),0,{}))