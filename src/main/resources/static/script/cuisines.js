// Loads cuisines 
const loadCuisines = () => {
	$.ajax({
		url: '/fetchcuisines',
		method: "GET",
		success(dataSet) {
			displayProductsDOM(dataSet);
		},
		error(err) {
			alert('Could not fetch cuisines !!', err);
		}
	});
};

// Renders cuisines list on DOM
function displayProductsDOM(dataSet) {
	$('#cuisines').DataTable({
		data: dataSet,
		columns: [{
			title: "Cuisine Name",
			"render": function (data, type) {
				if (type === 'display') {
					data = '<button class="button button1" value="' + data + '" onclick="getFoodItems(this)">' + data + '</button>';
				}
				return data;
			}
		}]
	});
}

loadCuisines();

function getFoodItems(e)
{
    localStorage.setItem("cuisineName", e.value);
    location.href = "/cuisinefooditems.html";
}