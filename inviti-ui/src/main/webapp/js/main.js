function getRestUrl() {
    return $('#inviti-rest-url').text();
}

function ping() {
    $.ajax({
            type: 'GET',
            url: getRestUrl() + '/user/test',
            success: function (data) {
                alert(data);
            }
        }
    );
}

jQuery(function ($) {
    $('#ping').click(ping);
});