import { ChangeDetectorRef, Component } from '@angular/core';
import { ILang } from '../interfaces/ilang';
import { TranslateService } from '@ngx-translate/core';
import { DateTimeAdapter } from 'ng-pick-datetime';

@Component({
  selector: 'app-lang-switcher',
  templateUrl: './lang-switcher.component.html',
  styleUrls: ['./lang-switcher.component.scss'],
})
export class LangSwitcherComponent {

  public lang: string;
  public supportedLanguages: Array<ILang>;

  constructor(
    private translate: TranslateService,
    private cdRef: ChangeDetectorRef,
    private dateAdapter: DateTimeAdapter<any>
  ) {
    setTimeout(() => {
      this.supportedLanguages = [
        {
          "code": "ru",
          "title": "Русский",
          "icon": "Russia-Flag-icon.png"
        },
        {
          "code": "en",
          "title": "English",
          "icon": "167794 - gb.png"
        },
        {
          "code": "de",
          "title": "Deutsch",
          "icon": "167808 - de.png"
        }
      ];
      const selectedLanguage = localStorage.getItem('language');
      if (selectedLanguage) {
        this.lang = selectedLanguage;
      } else {
        this.lang = this.supportedLanguages[0].code;
      }
      this.translate.use(this.lang);
      this.dateAdapter.setLocale(this.lang);
    }, 100);
  }

  changeLang(code: string) {
    this.lang = code;
    localStorage.setItem('language', this.lang);
    this.translate.use(this.lang);
    this.dateAdapter.setLocale(this.lang);
    this.cdRef.markForCheck();
  }

}
