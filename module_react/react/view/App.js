/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow
 */

import React, { Component } from 'react';
import { DeviceEventEmitter, StyleSheet, Text, View } from 'react-native';

export default class App extends Component {

  constructor(props) {
    super(props);
    this.state = {
      content: "App !",
    };
  }

  componentDidMount() {
    DeviceEventEmitter.addListener("react", (data) => this.renderTitle(data));
  }

  componentWillUnmount() {
    DeviceEventEmitter.removeListener("react", (data) => this.renderTitle(data));
  }

  renderTitle = (data) => {
    if (!data) return;
    const { react } = data;
    this.setState({ content: react });
  }

  render() {
    const { content } = this.state;
    return (
      <View style={styles.container}>
        <Text style={styles.welcome}>Welcome to React Native!</Text>
        <Text style={styles.welcome}>{content}</Text>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF'
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10
  }
});
