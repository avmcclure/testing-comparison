import React, { useState } from "react";

export function SearchInput(props) {
    const { search } = props;
    const [searchText, setSearchText] = useState("");

    function handleSearchInputOnChange(event) {
        setSearchText(event.target.value);
    }

    function handleSearchOnClick() {
        search(searchText);
    }

    return (
        <>
            <label htmlFor="search-input">Search: </label>
            <input id="search-input" value={searchText} onChange={handleSearchInputOnChange} />
            <button onClick={handleSearchOnClick}>Search</button>
        </>
    );
}
