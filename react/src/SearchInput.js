import React, { useState } from "react";

export function SearchInput(props) {
    const { search } = props;
    const [searchText, setSearchText] = useState("");

    const handleSearch = (text) => {
        search(text);
    };

    return (
        <>
            <input value={searchText} onChange={(event) => setSearchText(event.target.value)} />
            <button onClick={() => handleSearch(searchText)}>Search</button>
        </>
    );
}
