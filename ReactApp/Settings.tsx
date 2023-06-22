import AsyncStorage from '@react-native-async-storage/async-storage';
import {useSettingsContext, hostnameType} from './SettingsContext';

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

  private async storeHostnameSetting_impl(value: string) {
    try {
      await AsyncStorage.setItem(this.hostnameStoreKey, value);
    } catch (error) {
      console.error('Failed to store hostname setting:');
      console.error(error);
    }
  }

  private async loadHostnameSetting_impl() {
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

  public useStoreHostnameSetting = (value: string): hostnameType => {
    const {hostnameState, setHostnameState} = useSettingsContext();
    try {
      console.log(
        '`storeHostnameSetting` just called `storeHostnameSetting_impl`',
      );
      this.storeHostnameSetting_impl(value).then(() => {
        console.log(
          '`storeHostnameSetting` finished calling `storeHostnameSetting_impl`',
        );
        this.loadHostnameSetting_impl().then(loadedHostname => {
          console.log(
            `Updating \`useHostnameState\` with stored value: "${loadedHostname}"`,
          );
          setHostnameState(value);
        });
      });
    } catch (error) {
      console.log(
        '`storeHostnameSetting` failed call to `storeHostnameSetting_impl`',
      );
    }
    return {hostnameState, setHostnameState};
  };

  public useLoadHostnameSetting = (): hostnameType => {
    const {hostnameState, setHostnameState} = useSettingsContext();
    console.log('`loadHostnameSetting` just called `loadHostnameSetting_impl`');
    this.loadHostnameSetting_impl().then((value: string) => {
      console.log(
        `\`loadHostnameSetting\` finished calling \`loadHostnameSetting_impl\`. Received value: "${value}"`,
      );
      setHostnameState(value);
    });
    return {hostnameState, setHostnameState};
  };
}

export default Settings;
