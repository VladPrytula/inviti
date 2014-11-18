MyApp = {};
MyApp.vent = _.extend({}, Backbone.Events);

var AddUser = Backbone.Model.extend({
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
    el: addUserModal,

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

        $('#add-user-button').button('loading');
        this.model.save({
            name: $('#name').val(),
            email: $('#email').val(),
            password: $('#password').val()
        }, {
            success: function (model, resp) {
                //TODO a new sign 'User was successfully added.' should be displayed on #addUserModal window. Input fields should be removed as well as Submit button.
                $('#add-user-button').button('reset');
                //$('#addUserModal').modal('hide');
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

var LoginUser = Backbone.Model.extend({

    defaults: {
        loginName: '',
        loginPassword: ''
    },

    validate: function (attrs, options) {
        var errors = [];
        if (!attrs.loginName) {
            errors.push('loginName');
        }
        if (!attrs.loginPassword) {
            errors.push('loginPassword');
        }

        if (errors.length > 0){
            return errors;
        }
    },
    initialize: function () {
        this.on('invalid', function (model, errors) {
            $('#login-user-button').button('reset');
            this.showErrors(errors);
        });
    },

    showErrors: function (errors) {
        _.each(errors, function (error) {
            $('#'+error).parent().addClass('has-error');
        }, this);
    }

});


var LoginUserView = Backbone.View.extend({
    el: loginModal,

    events: {
        'click #login-user-button' : 'login',
        'click #not-registered-yet' : 'signup'
    },

    initialize: function (){
        this.hideErrors();
    },

    signup: function(){
       $('#loginModal').modal('hide');
       $('#addUserModal').modal('show');
    },

    login: function() {
        this.hideErrors();
        this.loginUser();
    },

    hideErrors: function (){
        $('#login-error').hide();
        $('.modal-body > div').removeClass('has-error');
    },

    //We can use jQuery ajax approach or backbone
    loginUser: function (error) {
        var self = this;
        this.model.set('loginName', $('#loginName').val());
        this.model.set('loginPassword', $('#loginPassword').val());
        if (this.model.isValid()) {

            $('#login-user-button').button('loading');

            var loginName = $('#loginName').val(),
                loginPassword = $('#loginPassword').val(),
                rememberCheck = ''
            $.ajax({
                    url: getRestUrl() + '/login',
                    type: 'POST',
                    dataType: "json",
                    contentType: 'application/json',
                    data: JSON.stringify({userName: loginName, password: loginPassword}),
                    success: function (data) {
                        if (data == true) {
                            self.handleResult(loginName);
                        } else {
                            self.showError('Invalid username or password')
                        }

                    },
                    error: function (xhr) {
                        self.showError('Error: ' + xhr.statusText + ' ' + xhr.status)
                    }
                }
            );
        } else {
            self.showError('Error: Name and Password can not be empty');
        }
    },

    handleResult: function (loginName){
        $('#add-user-button').button('reset');
        $('#loginModal').modal('hide');
        $("#signInButton").hide();
        $("#signUpButton").hide();
        $("#logOutButton").show();
        $("#userName").text(loginName);
        $("#userMenu").show();
        this.addSuggestedMeetings(loginName);
    },

    addSuggestedMeetings: function (loginName) {
        MyApp.vent.trigger('logged');

    },



    showError: function (error) {
        $('#login-user-button').button('reset');
        $('#login-error > label').text(error);
        $('#login-error').show();
    }
});

MyApp.vent.on('logged', function(){
    var homeUserView = new HomeUserView({model: loginUser});
});

var HomeUserView = Backbone.View.extend({

    events: {
    },

    initialize: function () {
        this.showSuggestedMeetings();
    },

    showSuggestedMeetings: function(){
        this.getUser();
        this.getMeetings();
        this.showMeetingByUserLocation();
        this.showMeetingsByInterests();
        this.showFriendsMeetings();
        alert('Home User View ran!');
    },

    getUser: function (loginName){
        var self = this;
        $.ajax({
                url: getRestUrl() + '/users/criterion;name=user12345',
                type: 'GET',
                dataType: "json",
                contentType: 'application/json',
                success: function (data) {
                    //populate user model

                },
                error: function (xhr) {

                }
            }
        );

    },

    getMeetings: function (){
        var self = this;
        $.ajax({
                url: getRestUrl() + '/meetings/criterion;username=user12345',
                type: 'GET',
                dataType: "json",
                contentType: 'application/json',
                success: function (data) {
                    //populate user model

                },
                error: function (xhr) {

                }
            }
        );

    },

    showMeetingByUserLocation: function () {
        var self = this;
            $.ajax({
                    url: getRestUrl() + '/meetings/criterion;location=US,NH,Portland',
                    type: 'GET',
                    dataType: "json",
                    contentType: 'application/json',
                     success: function (data) {


                    },
                    error: function (xhr) {

                    }
                }
            );

    },


    showMeetingsByInterests: function () {
        var self = this;
        $.ajax({
                url: getRestUrl() + '/meetings/criterion;interest=Food,Sport',
                type: 'GET',
                dataType: "json",
                contentType: 'application/json',
                success: function (data) {


                },
                error: function (xhr) {

                }
            }
        );

    },

    showFriendsMeetings: function (loginName) {
        var self = this;
        $.ajax({
                url: getRestUrl() + '/meetings/friends;userid=12345',
                type: 'GET',
                dataType: "json",
                contentType: 'application/json',
                success: function (data) {


                },
                error: function (xhr) {

                }
            }
        );

    }

});


var addUser = new AddUser();
var addUserView = new AddUserView({model: addUser});
var loginUser = new LoginUser();
var loginUserView = new LoginUserView({model: loginUser});
//var homeUserView = new HomeUserView({model: loginUser});
$("#logOutButton").hide();
$("#userMenu").hide();

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