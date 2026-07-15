declare module '@apiverve/vowelcounter' {
  export interface vowelcounterOptions {
    api_key: string;
    secure?: boolean;
  }

  /**
   * Describes fields the current plan does not unlock. Locked fields arrive as null
   * in `data`; `locked_fields` names them, using dot paths for nested fields.
   * Absent when the plan unlocks everything.
   */
  export interface PremiumInfo {
    message: string;
    upgrade_url: string;
    locked_fields: string[];
  }

  export interface vowelcounterResponse {
    status: string;
    error: string | null;
    data: VowelCounterData;
    code?: number;
    premium?: PremiumInfo;
  }


  interface VowelCounterData {
      vowels:              number | null;
      consonants:          number | null;
      totalLetters:        number | null;
      vowelPercentage:     number | null;
      consonantPercentage: number | null;
      vowelBreakdown:      VowelBreakdown;
      textLength:          number | null;
  }
  
  interface VowelBreakdown {
      a: number | null;
      e: number | null;
      i: number | null;
      o: number | null;
      u: number | null;
  }

  export default class vowelcounterWrapper {
    constructor(options: vowelcounterOptions);

    execute(callback: (error: any, data: vowelcounterResponse | null) => void): Promise<vowelcounterResponse>;
    execute(query: Record<string, any>, callback: (error: any, data: vowelcounterResponse | null) => void): Promise<vowelcounterResponse>;
    execute(query?: Record<string, any>): Promise<vowelcounterResponse>;
  }
}
