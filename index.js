/** @format */

import {AppRegistry} from 'react-native';
import App from './project/pages/index';
import {name as appName} from './app.json';
console.log("AppRegistry");
AppRegistry.registerComponent(appName, () => App);
