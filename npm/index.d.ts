declare module '@apiverve/vowelcounter' {
  export interface vowelcounterOptions {
    api_key: string;
    secure?: boolean;
  }

  export interface vowelcounterResponse {
    status: string;
    error: string | null;
    data: VowelCounterData;
    code?: number;
  }


  interface VowelCounterData {
      vowels:              number;
      consonants:          number;
      totalLetters:        number;
      vowelPercentage:     number;
      consonantPercentage: number;
      vowelBreakdown:      VowelBreakdown;
      textLength:          number;
  }
  
  interface VowelBreakdown {
      a: number;
      e: number;
      i: number;
      o: number;
      u: number;
  }

  export default class vowelcounterWrapper {
    constructor(options: vowelcounterOptions);

    execute(callback: (error: any, data: vowelcounterResponse | null) => void): Promise<vowelcounterResponse>;
    execute(query: Record<string, any>, callback: (error: any, data: vowelcounterResponse | null) => void): Promise<vowelcounterResponse>;
    execute(query?: Record<string, any>): Promise<vowelcounterResponse>;
  }
}
