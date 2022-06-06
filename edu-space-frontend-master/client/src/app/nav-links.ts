export const links: Array<any> = [
  {
    id: 'common',
    name: 'Common',
    icon: 'settings',
    items: []
  },
  {
    id: 'main',
    name: 'Main',
    icon: 'settings',
    items: [
      {
        id: 'course-list',
        name: 'MY_COURSES',
        link: 'course-list',
        icon: 'settings',
        permission: 'USER'
      }
    ]
  },
  {
    id: 'teacher',
    name: 'Teacher pages',
    icon: 'settings',
    items: [
      {
        id: 'edit-page',
        name: 'COURSE_PAGE',
        link: 'course-page',
        icon: 'settings',
        permission: 'TEACHER'
      }
    ]
  }
];
