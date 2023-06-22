import React from 'react';
import {StyleSheet, TouchableOpacity, View} from 'react-native';
import {SvgUri} from 'react-native-svg';

const SettingsButton = (props: {
  setInSettings: React.Dispatch<React.SetStateAction<boolean>>;
}) => {
  const {setInSettings} = props;

  const handleSettingsPress = async () => {
    console.log('User pressed the settings button');
    setInSettings(true);
  };

  return (
    <View style={styles.container}>
      <TouchableOpacity onPress={handleSettingsPress} style={styles.button}>
        <SvgUri width="100%" height="100%" uri="./gear-settings" />
      </TouchableOpacity>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    display: 'flex',
  },
  button: {
    padding: 10,
    backgroundColor: 'blue',
    borderRadius: 5,
    marginBottom: 10,
  },
  buttonLabel: {
    color: 'white',
    fontSize: 16,
  },
  input: {
    borderWidth: 1,
    borderColor: 'gray',
    borderRadius: 5,
    padding: 10,
    marginTop: 10,
    marginBottom: 10,
    width: 200,
    color: 'black',
  },
});

export default SettingsButton;
