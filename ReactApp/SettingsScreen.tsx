import React, {useState} from 'react';
import {
  StyleSheet,
  Text,
  TextInput,
  TouchableOpacity,
  View,
} from 'react-native';
import Section from './Section';
import Settings from './Settings';

const SettingsScreen = (props: {
  setInSettings: React.Dispatch<React.SetStateAction<boolean>>;
}) => {
  const {setInSettings} = props;

  const [hostnameValue, setHostnameValue] = useState('');

  const handleSubmitSettings = async () => {
    console.log('Trying to save hostname value:', hostnameValue);
    Settings.get().useStoreHostnameSetting(hostnameValue);
    setInSettings(false);
  };

  const handleInputChange = (text: string) => {
    setHostnameValue(text);
  };

  return (
    <Section title="Settings">
      <View style={styles.container}>
        <Text style={styles.settingText}>Hostname and Port</Text>
        <TextInput
          style={styles.input}
          value={hostnameValue}
          onChangeText={handleInputChange}
          onSubmitEditing={handleSubmitSettings}
          placeholder={Settings.get().useLoadHostnameSetting().hostnameState}
        />
        <TouchableOpacity onPress={handleSubmitSettings} style={styles.button}>
          <Text style={styles.buttonLabel}>Confirm</Text>
        </TouchableOpacity>
      </View>
    </Section>
  );
};

const styles = StyleSheet.create({
  container: {
    display: 'flex',
  },
  settingText: {
    flex: 1,
    fontWeight: 'bold',
    marginTop: 8,
    fontSize: 18,
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

export default SettingsScreen;
