module.exports = {
  displayName: {
    name: 'DENIOS',
    color: 'blue',
  },
  verbose: true,
  collectCoverage: true,
  coverageDirectory: './coverage',
  globals: {
    'ts-jest': {
      tsconfig: './tsconfig.spec.json',
      stringifyContentPathRegex: '\\.html$',
      astTransformers: [
        require.resolve('jest-preset-angular/build/InlineFilesTransformer'),
        require.resolve('jest-preset-angular/build/StripStylesTransformer')
      ]
    }
  },
  setupFilesAfterEnv: [
    '<rootDir>/node_modules/@angular-builders/jest/dist/jest-config/setup.js'
  ],
  transform: {
    '^.+\\.(ts|js|html)$': 'ts-jest'
  },
  testMatch: [
    '**/__tests__/**/*.+(ts|js)?(x)',
    '**/+(*.)+(spec|test).+(ts|js)?(x)'
  ],
  testEnvironment: 'jest-environment-jsdom-fifteen',
  moduleNameMapper: {
    '@app/(.*)': '<rootDir>/src/app/$1',
    '@env/(.*)': '<rootDir>/src/environments/$1',
    '@conf/(.*)': '<rootDir>/src/configuration/$1',
    '@angular/(.*)': '<rootDir>/node_modules/@angular/$1'
  },
  transformIgnorePatterns: ['node_modules/(?!@ngrx)'],
  collectCoverageFrom: [
    'src/app/**/*.ts',
    '!src/app/**/*.module.ts',
    '!src/app/**/*.array.ts',
    '!src/app/fragmentTypes.ts'
  ],
  moduleFileExtensions: ['ts', 'tsx', 'js', 'json'],
  testPathIgnorePatterns: ['/node_modules/', '/dist/', 'src/app/*.{js}'],
  snapshotSerializers: [
    'jest-preset-angular/build/AngularSnapshotSerializer.js',
    'jest-preset-angular/build/HTMLCommentSerializer.js'
  ]
};
