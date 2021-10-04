import React, {useCallback, useState} from 'react';
import {SearchInput} from "./SearchInput";
import {Photos} from "./Photos";

export function PhotoAlbum() {
    const [photos, setPhotos] = useState([]);

    const fetchPhotos = useCallback(async (searchText) => {
        try {
            const photos = await fetch(`https://jsonplaceholder.typicode.com/albums/${searchText}/photos`)
            const json = await photos.json();
            setPhotos(json);
        } catch (e) {
            console.error("Scott's fault", e)
        }
    }, [])

    return (
        <div>
            <SearchInput search={fetchPhotos}/>
            <Photos photos={photos}/>
        </div>
    );
}