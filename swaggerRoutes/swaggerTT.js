const getUsersTS = require('./user-swagger.js');
const getSubjectsTS = require('./subject-swagger.js');
const swaggerDocument = {
    openapi: '3.0.1',
    info: {
        version: '1.0.0',
        title: 'SchoolSpace Open Api',
        description: 'This is all the routes included in the server that you can use.',
        termsOfService: '',
        contact: {
            name: 'Abbes Mohamed',
            email: 'abbesmohamed45@gmail.com',
        },
        license: {
            name: 'Apache 2.0',
            url: 'https://www.apache.org/licenses/LICENSE-2.0.html'
        }
    },
    servers: [
        {
            url: 'https://project-android-sim-git-backend-abbesdev.vercel.app/',
            description: 'Production Server'
        }
    ],
    components: {
        securitySchemes: {
            bearerAuth: {
                type: 'apiKey',
                name: 'x-access-token',
                in: 'header'
            },
            security: {
                bearerAuth: []
            }
        }
    },
    tags: [
        {
            name: 'Users'
        }
    ],
    paths: {
        "/api/test/getallusers": {
            "get": getUsersTS
        },
        "/subject/getAll": {
            "get": getSubjectsTS
        }
    }
};
module.exports = swaggerDocument;