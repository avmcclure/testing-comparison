import React from 'react';

export function Photos(props) {
    const {photos} = props;
    return (
        <div style={{display: "grid", gridTemplateColumns: "1fr 1fr 1fr 1fr 1fr", margin: "1rem", columnGap: "1rem"}}>
            {photos.map(photo => <Photo key={photo.id} photo={photo}/>)}
        </div>
    );
}

function Photo(props) {
    const {photo} = props;

    return <div style={{display: "flex", flexDirection: "column"}}>
        <img src={photo.thumbnailUrl}/>
        <span>Album ID: {photo.albumId}</span>
        <span>Photo ID: {photo.id}</span>
        <span>Title: {photo.title}</span>
    </div>
}