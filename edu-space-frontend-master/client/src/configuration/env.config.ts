export const environment: any = {
  production: false
};

export class Configuration {

  // Supported languages
  public static GET_SUPPORTED_LANGUAGES() {
    return [
      { code: 'ru', title: 'Русский', icon: 'Russia-Flag-icon.png' },
      { code: 'en', title: 'English', icon: '167794 - gb.png' },
      { code: 'de', title: 'German',  icon: '167808 - de.png' }
    ];
  }

  // Application locale used for datetime input formats
  // Unicode locale identifiers) based on the norm BCP47
  public static GET_APP_LOCALE() {
    return 'ru';
  }

  // Datetime custom native formats
  public static GET_DATETIME_FORMATS() {
    return {
      fullPickerInput: {year: 'numeric', month: 'numeric', day: 'numeric', hour: 'numeric', minute: 'numeric'},
      datePickerInput: {year: 'numeric', month: 'numeric', day: 'numeric'},
      timePickerInput: {hour: 'numeric', minute: 'numeric'},
      monthYearLabel: {year: 'numeric', month: 'numeric'},
      dateA11yLabel: {year: 'numeric', month: 'long', day: 'numeric'},
      monthYearA11yLabel: {year: 'numeric', month: 'long'},
    };
  }
}
