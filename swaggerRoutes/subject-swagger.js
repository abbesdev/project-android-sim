const getSubjects = {
    tags: ['Subjects'],
    description: "Returns all subjects from the system that the user has access to",
    operationId: 'getSubjects',
    security: [
        {
            bearerAuth: []
        }
    ],
    responses: {
        "200": {          
            description: "A list of subjects.",
            "content": {
                "application/json": {
                    schema: {
                        type: "array",
                        items: {
                            nameSubject: {
                                type: 'string',
                                description: 'subject name'
                            },
                            imageSubject: {
                                type: 'string',
                                description: 'subject image url'
                            }
                        }
                    }
                }
            }
        }
    }
} 
module.exports = getSubjects;