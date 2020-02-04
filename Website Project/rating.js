$(document).ready(function () {

    $('img').hover(
    function () {
        $(this).stop().fadeOut(function () {
            var $temp = $(this).attr('src');
            $(this).attr('src', $(this).attr('data-alt-src'));
            $(this).attr('data-alt-src', $temp);
        });

        $(this).fadeIn();
    },

    function () {
        $(this).stop().fadeOut(function () {
            var $temp = $(this).attr('data-alt-src');
            $(this).attr('data-alt-src', $(this).attr('src'));
            $(this).attr('src', $temp);
        });

        $(this).fadeIn();

    });
});