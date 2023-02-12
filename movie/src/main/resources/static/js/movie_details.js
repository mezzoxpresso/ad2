$(document).ready(function() {
		
	$('#inputReview').rating().on("rating:change", function(event, value, caption) {
		var updateRating = {};
		updateRating["ratingValue"] = this.value;

		updateRating["movieId"] = document.getElementById("movieId").value;

		$.ajax({
			type: "POST",
			contentType: "application/json",
			url: "/rating",
			data: JSON.stringify(updateRating),
			dataType: 'json',
			cache: false,
			timeout: 600000,
			success: function(data) {
				$('#movieReview').rating('update', JSON.stringify(data));
				$('#inputReview').rating('update', 0);

			},
			error: function(e) {
				window.location.href = e.responseText;
			}
		});


	});
	// hide the clear button.
	$('#inputReview').rating('refresh', { disabled: false, showClear: false, showCaption: false });

	$('#movieReview').rating('refresh', { disabled: true, showClear: false, showCaption: true });


	$('.btn-edit').click(function() {
		//apply custom values where needed
		var $btn = $(this);
		var reviewId = $btn.attr('data-reviewId');
		var inputId = reviewId+'input';
		var saveId = reviewId+'save';
		var comment = $btn.attr('data-comment');

		document.getElementById(inputId).value = comment;
		document.getElementById(inputId).style.display='block';
		
		document.getElementById(saveId).style.display='block';
		document.getElementById(reviewId).style.display='none';
		
		document.getElementById('edit').style.display = 'none';
	});

});
