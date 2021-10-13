import React, { useState } from "react";
import { SearchInput } from "./SearchInput";
import { Photos } from "./Photos";
import { get } from "./common/fetchService";

export function PhotoAlbum() {
    const [photos, setPhotos] = useState([]);

    const fetchPhotos = async (searchText) => {
        const photos = await get(`https://jsonplaceholder.typicode.com/albums/${searchText}/photos`);
        setPhotos(photos);
    };

    return (
        <div>
            <SearchInput search={fetchPhotos} />
            <Photos photos={photos} />
        </div>
    );
}
