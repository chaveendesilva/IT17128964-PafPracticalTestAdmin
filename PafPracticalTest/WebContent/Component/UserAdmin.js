$(document).ready(function()
{
	if ($("#alertSuccess").text().trim() == "")
	{
		$("#alertSuccess").hide();
	}
	
	$("#alertError").hide();
});

//SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
// Clear alerts---------------------
 $("#alertSuccess").text("");
 $("#alertSuccess").hide();
 $("#alertError").text("");
 $("#alertError").hide(); 
 

// Form validation-------------------
var status = validateAdminForm();

if (status != true)
     {
      $("#alertError").text(status);
      $("#alertError").show();
      
 return;
 }


// If valid-------------------------
var method = ($("#hidAdminIDSave").val() == "") ? "POST" : "PUT" ;

$.ajax( 
		{
			url : "AdminAPI",
			type : method,
			data : $("#formAdmin").serialize(),
			dataType : "text",
			complete : function(response, status)
			{
				onAdminSaveComplete(response.responseText, status);
			}
		});

});

//UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
 $("#hidAdminIDSave").val($(this).closest("tr").find('#hidAdminIDUpdate').val());
 $("#adminUsername").val($(this).closest("tr").find('td:eq(0)').text());
 $("#adminPassword").val($(this).closest("tr").find('td:eq(1)').text());
 $("#adminReports").val($(this).closest("tr").find('td:eq(2)').text());

});


function onAdminSaveComplete(response, status)
{
   if (status == "success")
        {
         var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
     {
       $("#alertSuccess").text("Successfully saved.");
       $("#alertSuccess").show();
       $("#divAdminGrid").html(resultSet.data);
 } 
 else if (resultSet.status.trim() == "error")
	 
        {
          $("#alertError").text(resultSet.data);
          $("#alertError").show();
 }
 } 
   else if (status == "error")
        {
             $("#alertError").text("Error while saving.");
             $("#alertError").show();
 }
   else
     {
       $("#alertError").text("Unknown error while saving..");
       $("#alertError").show();
 }
       $("#hidAdminIDSave").val("");
       $("#formAdmin")[0].reset();
}




// REMOVE ===============
$(document).on("click", ".btnRemove", function(event)
		{
		 $.ajax(
		 {
		 url : "AdminAPI",
		 type : "DELETE",
		 data : "adminID=" + $(this).data("adminid"),
		 dataType : "text",
		 complete : function(response, status)
		 {
		 onAdminDeleteComplete(response.responseText, status);
		 }
		 });
});


function onAdminDeleteComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully deleted.");
 $("#alertSuccess").show();
 $("#divAdminGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while deleting.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while deleting..");
 $("#alertError").show();
 }
}


function validateAdminForm()
{
// USERNAME-------------------
if ($("#adminUsername").val().trim() == "")
 {
 return "Insert Admin Username.";
 }
// PASSWORD-----------------
if ($("#adminPassword").val().trim() == "")
 {
 return "Insert Admin Password.";
 }
// Reports-------------------------------
if ($("#adminReorts").val().trim() == "")
 {
 return "Insert Admin Reports.";
 }

return true;
}
