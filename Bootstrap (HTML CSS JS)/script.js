var last_id = 0

function Add_task() {
	if(document.forms["task_form"]["task_descr"].value != "" && document.forms["task_form"]["task_title"].value	!= "")
	{
	last_id = last_id + 1;
	
	//problème de sécurité : possibilité de passer du html
	var task_descr =  document.forms["task_form"]["task_descr"].value;
	document.forms["task_form"]["task_descr"].value	= "";
	var task_title =  document.forms["task_form"]["task_title"].value;
	document.forms["task_form"]["task_title"].value = "";

	
	var task_board = document.getElementById("tasks_board");
	let task_card = document.createElement("div");
	
	task_card.setAttribute("class", "col-12 col-lg-4");
	
	task_card.setAttribute("id", "task_"+last_id);
	
	task_card.innerHTML = '\
		<div class="card">\
		<div class="card-body">\
			<h5 class="card-title">' + task_title + '</h5>\
			<p class="card-text">' + task_descr + '</p>\
		</div>\
		<div class="card-footer text-muted">\
            <button type="button" class="btn btn-outline-secondary" onclick="Del_task('+ last_id +')">Supprimer</button>\
		</div>\
		</div>\
	';
	
	task_board.appendChild(task_card);
	}
}

function Del_task(id) {
	var task_board = document.getElementById("task_"+id);
	task_board.remove(task_board.selectedIndex); 
}

