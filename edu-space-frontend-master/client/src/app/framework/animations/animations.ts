import { animate, group, state, style, transition, trigger } from '@angular/animations';

export const ToolbarAnimation = [
  trigger('slideInOut', [
    state('in', style({
      'max-height': '68px', 'visibility': 'visible'
    })),
    state('out', style({
      'max-height': '0px', 'visibility': 'hidden'
    })),
    transition('in => out', [group([
        animate('300ms 500ms ease-in-out', style({
          'max-height': '0px'
        })),
        animate('0ms 800ms linear', style({
          'visibility': 'hidden'
        }))
      ]
    )]),
    transition('out => in', [group([
        animate('500ms 500ms ease-in-out', style({
          'max-height': '500px'
        })),
        animate('0ms 0ms linear', style({
          'visibility': 'visible'
        }))
      ]
    )])
  ]),
];
