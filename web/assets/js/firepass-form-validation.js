$(document).ready(function() {

    
    $('#login').bootstrapValidator({
        fields: {
            email: {
                validators: {
                    notEmpty: {
                        message: 'The email address is required'
                    },
                    emailAddress: {
                        message: 'The input is not a valid email address'
                    }
                }
            },
            password: {
                validators: {
                    notEmpty: {
                        message: 'Password is required and cannot be empty'
                    }
                }
            }
            
        }
    });
    
    $('#resource-save').bootstrapValidator({
        fields: {
            title: {
                validators: {
                    notEmpty: {
                        message: 'This field is required and cannot be empty'
                    }
                }
            },
            resource_group_id: {
                validators: {
                    notEmpty: {
                        message: 'This field is required'
                    }
                }
            }
            
        }
    });
    
    $('#resource-group-save').bootstrapValidator({
        fields: {
            title: {
                validators: {
                    notEmpty: {
                        message: 'This field is required and cannot be empty'
                    }
                }
            }
            
        }
    });
    
    $('#user-group-save').bootstrapValidator({
        fields: {
            name: {
                validators: {
                    notEmpty: {
                        message: 'This field is required and cannot be empty'
                    }
                }
            }
            
        }
    });
    
    $('#user-save').bootstrapValidator({
        fields: {
            firstname: {
                validators: {
                    notEmpty: {
                        message: 'Password is required and cannot be empty'
                    }
                }
            },
            lastname: {
                validators: {
                    notEmpty: {
                        message: 'Password is required and cannot be empty'
                    }
                }
            },
            email: {
                validators: {
                    notEmpty: {
                        message: 'The email address is required'
                    },
                    emailAddress: {
                        message: 'The input is not a valid email address'
                    }
                }
            },
            group_id: {
                validators: {
                    notEmpty: {
                        message: 'Password is required and cannot be empty'
                    }
                }
            }
        }
    });
    
    $('#settings').bootstrapValidator({
        firstname: {
                validators: {
                    notEmpty: {
                        message: 'Password is required and cannot be empty'
                    }
                }
            },
            lastname: {
                validators: {
                    notEmpty: {
                        message: 'Password is required and cannot be empty'
                    }
                }
            },
            email: {
                validators: {
                    notEmpty: {
                        message: 'The email address is required'
                    },
                    emailAddress: {
                        message: 'The input is not a valid email address'
                    }
                }
            },
    });
});