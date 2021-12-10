var path = require('path');

module.exports = {
    entry: '../react-frontend/my-app/src/index.js',
    devtool: 'sourcemaps',
    cache: true,
    mode: 'development',
    output: {
        path: __dirname,
        filename: './src/main/resources/static/built/bundle.js'
    },
    module: {
        rules: [
            {
                test: /\.js$/,
                exclude: [/node_modules/],
                loader: 'babel-loader',
                options: {
                    presets: ["@babel/preset-env", ["@babel/preset-react", {"runtime": "automatic"}]]
                }
            },
            {
                test: /\.css$/i,
                use: ["style-loader","css-loader"]
            },
        ]
    }
};