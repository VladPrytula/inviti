var User = Backbone.Model.extend({
    url: $('#inviti-rest-url').text() + '/adduser',

    defaults: {
        name: '',
        email: '',
        password: ''
    },

    validate: function (attrs, options) {
        var errors = [];
        if (!attrs.name) {
           // $('.modal-body > div').addClass('has-error');
            errors.push('name');
            //return 'Name can not be empty!';
        }
        if (!attrs.email) {
            errors.push('email');
            //return 'Email can not be empty!';
        }

        if (!attrs.password) {
            errors.push('password');
            //return 'Password can not be empty!';
        }

        if (errors.length > 0){
            return errors;
        }

    },
    initialize: function () {
        this.on('invalid', function (model, errors) {
            $('#add-user-button').button('reset');
            this.showErrors(errors);
        });
    },

    showErrors: function (errors) {
        _.each(errors, function (error) {
            $('#'+error).parent().addClass('has-error');
            $('#'+error).parent().find('label').text('Can not be empty!');
        }, this);
    }

});

var AddUserView = Backbone.View.extend({
    el: myModal,

    events: {
        'click #add-user-button' : 'submit'
    },

    initialize: function (){
        this.hideErrors();
    },

    //we can use a kind of mapping model's attributes to UI html components.
    render: function (){
        this.$el.html(this.model.toJSON());
    },

    submit: function() {
        //this.render();
        this.hideErrors();
        this.addUser();
    },

    hideErrors: function (){
        $('#server-error').hide();
        $('.modal-body > div').removeClass('has-error');
        $('.modal-body > div').find('label').text('');
    },

    //We can use jQuery ajax approach or backbone
    addUser: function(error){
        /* $.ajax({
         url: getRestUrl() + '/adduser',
         type:'POST',
         dataType:"json",
         data: this.model.toJSON(),
         success: function (data) {
         alert(data);
         },
         error: function(){
         }
         }
         );*/

        $('#add-user-button').button('loading');
        this.model.save({
            name: $('#name').val(),
            email: $('#email').val(),
            password: $('#password').val()
        }, {
            success: function (model, resp) {
                //TODO a new sign 'User was successfully added.' should be displayed on #MyModal window. Input fields should be removed as well as Submit button.
                $('#add-user-button').button('reset');
                //$('#myModal').modal('hide');
                console.log('User was successfully added.');
            },
            error: function (model, xhr, options) {
                $('#add-user-button').button('reset');
                $('#server-error > label').text('Error: ' + xhr.statusText + ' ' + xhr.status)
                $('#server-error').show();
            }
        });
    }


});

var user = new User();
var addUserView = new AddUserView({model: user});

function getRestUrl() {
    return $('#inviti-rest-url').text();
}

function ping() {
    $.ajax({
            type: 'GET',
            url: getRestUrl() + '/ping',
            success: function (data) {
                alert(data);
            }
        }
    );
}

jQuery(function ($) {
    $('#ping').click(ping);
});