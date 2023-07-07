import React from 'react';
import {StyleSheet, Text, TouchableOpacity, View} from 'react-native';

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
        {/* <Text style={styles.buttonLabel}>&#x26&#x99&#xFE&#x0F</Text> */}
        <Text style={styles.buttonLabel}>⚙️</Text>
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
    backgroundColor: '#E5E5E5',
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
