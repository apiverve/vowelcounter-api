using System;
using System.Collections.Generic;
using System.Text;
using Newtonsoft.Json;

namespace APIVerve.API.VowelCounter
{
    /// <summary>
    /// Query options for the Vowel Counter API
    /// </summary>
    public class VowelCounterQueryOptions
    {
        /// <summary>
        /// Text to analyze (max 50,000 characters)
        /// </summary>
        [JsonProperty("text")]
        public string Text { get; set; }
    }
}
