function editProfileInfo() {
	
const userData = toJSONString(document.querySelector("form.user-info"));
	

fetch('updateUserInfo', {
  method: 'POST',
  headers: {
    'Content-Type': 'application/json',
  },
  body: userData 
})
.then((response) => response.json())
.then((data) => {
   document.getElementById("result").innerHTML=data.message;
})
.catch((error) => {
  console.error('Error:', error);
});

}

  function toJSONString(form) {
	let obj = {};
	let elements = form.querySelectorAll("input");
	for(var i = 0; i < elements.length; i++) {
		var element = elements[i];
		var name = element.name;
		var value = element.value;

		if(name) {
			obj[name] = value;
		}
	}

	return JSON.stringify(obj);
}