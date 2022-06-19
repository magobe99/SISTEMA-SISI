'use strict';

var singleUploadForm = document.querySelector('#singleUploadForm');
var singleFileUploadInput = document.querySelector('#singleFileUploadInput');
var singleFileUploadError = document.querySelector('#singleFileUploadError');
var singleFileUploadSuccess = document.querySelector('#singleFileUploadSuccess');


function uploadSingleFile(file) {
    var formData = new FormData();
    formData.append("file", file);

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/api/csv/upload");

    xhr.onload = function() {
        console.log(xhr.responseText);
        var response = JSON.parse(xhr.responseText);
        if(xhr.status == 200) {
            singleFileUploadError.style.display = "ninguno";
            singleFileUploadSuccess.innerHTML = "<p>El archivo se ha subido correctamente.</p><p>URL de descarga : <a href='" + response.fileDownloadUri + "' target='_blank'>" + response.fileDownloadUri + "</a></p>";
            singleFileUploadSuccess.style.display = "bloquear";
        } else {
            singleFileUploadSuccess.style.display = "ninguno";
            singleFileUploadError.innerHTML = "<p>"+(response && response.message)+"<p>" || "Ocurrió algún error";
            singleFileUploadSuccess.style.display = "bloquear";

        }
    }

    xhr.send(formData);
}



singleUploadForm.addEventListener('submit', function(event){
    var files = singleFileUploadInput.files;
    if(files.length === 0) {
        singleFileUploadError.innerHTML = "Seleccione un archivo";
        singleFileUploadError.style.display = "bloquear";
    }
    uploadSingleFile(files[0]);
    event.preventDefault();
}, true);


