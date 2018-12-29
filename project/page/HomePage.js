/**
 * Created by inshow on 2018/12/29.
 */
import React from 'react';
import {StyleSheet, Text, View, TouchableOpacity, Button, NativeModules} from 'react-native';

export default class HomePage extends React.Component {
    render() {
        return (
            <View style={{flex: 1, alignItems: 'center', justifyContent: 'center'}}>
                <Button
                    title="Home"
                    onPress={() => {
                    }}
                />
            </View>
        );
    }
}