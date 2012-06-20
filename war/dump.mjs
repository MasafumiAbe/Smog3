function printApplication() {
	var names = application.iterateAttributeNames();
	while (names.hasNext()) {
		var name = names.next();
		print("app: " + name + " = " + application[name]);
	}
}

function printSession() {
	var names = session.iterateAttributeNames();
	while (names.hasNext()) {
		var name = names.next();
		print("ses: " + name + " = " + session[name]);
	}
}

function printAttribute() {
	var names = request.iterateAttributeNames();
	while (names.hasNext()) {
		var name = names.next();
		print("att: " + name + " = " + request[name]);
	}
}

function printParameter() {
	var names = param.iterateAttributeNames();
	while (names.hasNext()) {
		var name = names.next();
		print("prm: " + name + " = " + param[name]);
	}
}

function printHeader() {
	var names = header.iterateAttributeNames();
	while (names.hasNext()) {
		var name = names.next();
		print("hed: " + name + " = " + header[name]);
	}
}

function printBinding() {
	var names = binding.iterateAttributeNames();
	while (names.hasNext()) {
		var name = names.next();
		print("bid: " + name + " = " + binding[name]);
	}
}

function print(value) {
	java.lang.System.out.println(new String(value));
}

print("------ req-path: " + request.getRequestedPath());
printSession();
printAttribute();
printParameter();
printHeader();
printBinding();
