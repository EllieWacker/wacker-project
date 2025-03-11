<c:set var="test" value="Kirkwood Eagles"></c:set>
<div class="container">
    <h2>${test}</h2>
    <ul>
        <li>Contains "Kirkwood": ${fn:contains(test, "Kirkwood")}</li>
        <li>Contains "kirkwood" regardless of capitalization: ${fn:containsIgnoreCase(test, "kirkwood")}</li>
        <li>To lowercase: ${fn:toLowerCase(test)}</li>
        <li>To uppercase: ${fn:toUpperCase(test)}</li>
        <li>Starts with "Kirkwood": ${fn:startsWith(fn:toLowerCase(test), "kirkwood")}</li>
        <li>Ends with "Kirkwood": ${fn:endsWith(fn:toLowerCase(test), "kirkwood")}</li>
        <li>Starting index of "Kirkwood": ${fn:indexOf(fn:toLowerCase(test), "kirkwood")}</li>
        <li>Length: ${fn:length(test)}</li>
        <li>"wood" changed to "land": ${fn:replace(fn:toLowerCase(test), "wood", "land")}</li>
        <li>Starts with "K": ${fn:startsWith(fn:toLowerCase(test), "k")}</li>
        <li>First 4 characters: ${fn:substring(test, 0, 4)}</li>
        <li>Characters after "Kirk": ${fn:substringAfter(fn:toLowerCase(test), "kirk")}</li>
        <li>Characters before "wood": ${fn:substringBefore(fn:toLowerCase(test), "wood")}</li>
        <li>White space removed from both ends: ${fn:trim(test)}</li>
        <c:set var="numWords" value="${fn:split(test, ' ')}" /> <!--Splits a string into an array of substrings-->
        <li>Number of words: ${fn:length(numWords)}</li> <!--Then finds the length of the array.-->
        <li>Words joined with a forward slash: ${fn:join(numWords, "/")}</li>
    </ul>
</div>