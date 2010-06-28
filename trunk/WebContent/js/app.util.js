function UR_Start() 
{
	UR_Nu = new Date;
	UR_Indhold = showFilled(UR_Nu.getHours()) + ":" + showFilled(UR_Nu.getMinutes()) + ":" + showFilled(UR_Nu.getSeconds());
	document.getElementById("ur").innerHTML = UR_Indhold;
	setTimeout("UR_Start()",1000);
}
function showFilled(Value) 
{
	return (Value > 9) ? "" + Value : "0" + Value;
}

function showData()
{
	var day_nme = new Array("Domingo", "Segunda-feira", "Terça-feira", "Quarta-feira", "Quinta-feira", "Sexta-feira", "Sábado");
	var month_nme = new Array("", "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro");
	var now = new Date();
	var nowDay = now.getDay();
	var nowDate = now.getDate();
	var nowMonth = now.getMonth() + 1;
	var nowYear = now.getFullYear();
	var data = day_nme[nowDay] + ', ' + nowDate + ' de ' + month_nme[nowMonth] + ' de ' + nowYear;
	
	document.getElementById("dt").innerHTML = data;
}