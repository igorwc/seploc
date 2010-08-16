<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j" %>
<html>
	<head>
		<title>Minha Primeira Página com JBoss RichFaces</title>
	</head>
	<body>
		<f:view>
			<h:form>
				Digite seu texto aqui:
				<h:inputText id="texto" value="#{textoBean.texto}">
				<a4j:support event="onkeyup" reRender="resultado" />
				</h:inputText>
				<br />Resultado:
				<strong> 
					<h:outputLabel id="resultado" value="#{textoBean.texto}" /> 
				</strong>
			</h:form>
		</f:view>
	</body>
</html>
