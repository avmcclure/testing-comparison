import React, { useState } from "react";
import { SearchInput } from "./SearchInput";
import { Photos } from "./Photos";
import { get } from "./common/fetchService";
import "./PhotoAlbum.css";

export function PhotoAlbum() {
    const [photos, setPhotos] = useState([]);

    const fetchPhotos = async (searchText) => {
        const photos = await get(`http://localhost:8080/album/${searchText}/photos`);
        setPhotos(photos);
    };

    return (
        <main className="mt-1">
            <SearchInput search={fetchPhotos} />
            <Photos photos={photos} />
        </main>
    );
}
