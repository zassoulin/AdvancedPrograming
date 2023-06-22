import AsyncStorage from '@react-native-async-storage/async-storage';

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

  private async storeHostnameSetting_impl(value: string) {
    try {
      await AsyncStorage.setItem(this.hostnameStoreKey, value);
    } catch (error) {
      console.error('Failed to store hostname setting:');
      console.error(error);
    }
  }

  private async loadHostnameSetting_impl() {
    // Default value of 'localhost:8080'
    let hostname = 'localhost:8080';
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

  public storeHostnameSetting(value: string) {
    try {
      console.log(
        '`storeHostnameSetting` just called `storeHostnameSetting_impl`',
      );
      this.storeHostnameSetting_impl(value).then(() => {
        console.log(
          '`storeHostnameSetting` finished calling `storeHostnameSetting_impl`',
        );
      });
    } catch (error) {
      console.log(
        '`storeHostnameSetting` failed call to `storeHostnameSetting_impl`',
      );
    }
  }

  public loadHostnameSetting(): string {
    // Default value of 'localhost:8080'
    let hostname = 'localhost:8080';
    console.log('`loadHostnameSetting` just called `loadHostnameSetting_impl`');
    this.loadHostnameSetting_impl().then((value: string) => {
      console.log(
        `\`storeHostnameSetting\` finished calling \`storeHostnameSetting_impl\`. Received value: "${value}"`,
      );
      hostname = value;
    });
    return hostname;
  }
}

export default Settings;
