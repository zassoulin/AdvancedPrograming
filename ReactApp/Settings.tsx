import AsyncStorage from '@react-native-async-storage/async-storage';
import {hostnameType} from './SettingsContext';

class Settings {
  private static instance: Settings;
  private constructor() {}
  public static get(): Settings {
    if (!Settings.instance) {
      Settings.instance = new Settings();
    }
    return Settings.instance;
  }

  private hostnameStoreKey = '@hostname_key';
  private defaultHostname = 'localhost:8080';

  public async storeHostnameSetting(value: string) {
    try {
      await AsyncStorage.setItem(this.hostnameStoreKey, value);
    } catch (error) {
      console.error('Failed to store hostname setting:');
      console.error(error);
    }
  }

  public async loadHostnameSetting() {
    let hostname = this.defaultHostname;
    try {
      const value = await AsyncStorage.getItem(this.hostnameStoreKey);
      if (value !== null) {
        console.log(`Loaded hostname="${value}"`);
        hostname = value;
      }
    } catch (error) {
      console.error('Failed to load the hostname setting:');
      console.error(error);
    }
    return hostname;
  }

  public useStoreHostnameSetting = (
    hostnameStruct: hostnameType,
    value: string,
  ): hostnameType => {
    const {hostnameState, setHostnameState} = hostnameStruct;
    console.log('`useStoreHostnameSetting` just called `storeHostnameSetting`');
    if (value.trim() === '') {
      this.loadHostnameSetting().then(loadedHostname => {
        console.log(
          `Given value is empty, keeping hostname as is: "${loadedHostname}"`,
        );
        setHostnameState(value);
      });
    } else {
      this.storeHostnameSetting(value).then(() => {
        console.log(
          '`useStoreHostnameSetting` finished calling `storeHostnameSetting`',
        );
        this.loadHostnameSetting().then(loadedHostname => {
          console.log(
            `Updating \`hostnameState\` with stored value: "${loadedHostname}"`,
          );
          setHostnameState(value);
        });
      });
    }
    return {hostnameState, setHostnameState};
  };

  public useLoadHostnameSetting = (
    hostnameStruct: hostnameType,
  ): hostnameType => {
    const {hostnameState, setHostnameState} = hostnameStruct;
    console.log('`useLoadHostnameSetting` just called `loadHostnameSetting`');
    this.loadHostnameSetting().then((value: string) => {
      console.log(
        `\`useLoadHostnameSetting\` finished calling \`loadHostnameSetting\`. Received value: "${value}"`,
      );
      setHostnameState(value);
    });
    return {hostnameState, setHostnameState};
  };
}

export default Settings;
