 const getUsers = {
    tags: ['Users'],
    description: "Returns all users from the system that the user has access to",
    operationId: 'getUsers',
    security: [
        {
            bearerAuth: []
        }
    ],
    responses: {
        "200": {          
            description: "A list of users.",
            "content": {
                "application/json": {
                    schema: {
                        type: "array",
                        items: {
                            email: {
                                type: 'string',
                                description: 'User Email'
                            },
                            fullname: {
                                type: 'string',
                                description: 'User Fullname'
                            }
                        }
                    }
                }
            }
        }
    }
} 
module.exports = getUsers;