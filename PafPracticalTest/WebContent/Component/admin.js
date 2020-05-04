$(document).ready(function()
		{
	         $("#alertSuccess").hide();
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
var status = validatedocpaymentForm();

if (status != true)
     {
      $("#alertError").text(status);
      $("#alertError").show();
      
 return;
 }


// If valid-------------------------
var type = ($("#hidAdminIDSave").val() == "") ? "POST" : "PUT" ;

$.ajax( 
		{
			url : "AdminAPI",
			type : type,
			data : $("#formAdmin").serialize(),
			dataType : "text",
			complete : function(response, status)
			{
				onAdminSaveComplete(response.responseText, status);
			}
		});

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
       $("#divdocpaymentGrid").html(resultSet.data);
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
       $("#formdAdmin")[0].reset();
}

//UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
 $("#hidAdminIDSave").val($(this).closest("tr").find('#hidAdminIDUpdate').val());
 $("#adminUsername").val($(this).closest("tr").find('td:eq(0)').text());
 $("#adminPassword").val($(this).closest("tr").find('td:eq(1)').text());
 $("#adminReports").val($(this).closest("tr").find('td:eq(2)').text());

});


// REMOVE ===============
$(document).on("click", ".btnRemove", function(event)
		{
		 $.ajax(
		 {
		 url : "AdminAPI",
		 type : "DELETE",
		 data : "admintID=" + $(this).data("admintID"),
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
