$(document).ready(function () {
    load();
});
var result = []
var slideIndex = 1;
showSlides(slideIndex);

function plusSlides(n) {
    showSlides(slideIndex += n);
}

function currentSlide(n) {
    showSlides(slideIndex = n);
}

function showSlides(n) {
    var i;
    var slides = document.getElementsByClassName("slide");
    //var allPics = document.getElementsByClassName("pic");
    var captionTitle = document.getElementById("title");
    var captionGenre = document.getElementById("genre");
    if (n > slides.length) {slideIndex = 1}
    if (n < 1) {slideIndex = slides.length}
    for (i = 0; i < slides.length; i++) {
        slides[i].style.display = "none";
    }
    // for (i = 0; i < allPics.length; i++) {
    //     allPics[i].className = allPics[i].className.replace(" active", "");
    // }
    slides[slideIndex-1].style.display = "flex";
    // allPics[slideIndex-1].className += " active";
    captionTitle.innerHTML = result[slideIndex-1].title;
    captionGenre.innerHTML = result[slideIndex-1].genre;
}

function load() {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "http://localhost:8080/mfpc/movie",
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {

            var json = "<h4>Ajax Response</h4>"
                + JSON.stringify(data, null, 4) ;
            // $('#feedback').html(json);

            console.log("SUCCESS : ", data);
            result = JSON.parse(JSON.stringify(data));

            // var posters = result.map(function (e) {
            //     return e.posterPath;
            // });
            // posters.map(function (e ) {
            //     return "<a href='" + posters[0] + "'>idk</a>";
            // });

            var galleryBig = result.map(function (e) {
                return "<div class=slide><img src='" + e.posterPath + "'></div>"
            });

            var thumbnails = result.map(function (e) {
                currentIndex = result.indexOf(e) + 1
                return " <div class=column>\n" +
                    "                    <img class=\"pic cursor\" src="+ e.posterPath + "] style=\"width:100%;height:330px\"\n" +
                    "                    onclick=currentSlide("+ currentIndex +") alt="+ e.title + ">\n" +
                    "     </div>"
            });

            $(".gallery")
                .prepend(galleryBig)
                //.append("<img src='" + posters[0] + "'>idk</img>");


            $(".row")
                .append(thumbnails)

            currentSlide(1);

            //.append("<a href='" + posters[0] + "'>idk</a>");

        },
        error: function (e) {
            console.log("ERROR : ", e);
        }
    });
}

function random() {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "http://localhost:8080/mfpc/movie/random",
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            console.log(data)
            movie = JSON.parse(JSON.stringify(data));
            currentSlide(movie.id + 1)
        },
        error: function (e) { console.log("ERROR : ", e); }
    });
}