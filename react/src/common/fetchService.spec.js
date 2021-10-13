import { get } from "./fetchService";
import { rest } from "msw";
import { setupServer } from "msw/node";
import { createPhotoResponse } from "./objectBuilders";

it("should return json formatted from fetch", async () => {
    const albumId = 1;
    const expectedResponse = createPhotoResponse(albumId);

    const result = await get(`https://jsonplaceholder.typicode.com/albums/${albumId}/photos`);

    expect(result).toEqual([expectedResponse]);
});

const handlers = [
    rest.get(`https://jsonplaceholder.typicode.com/albums/:albumId/photos`, async (req, res, ctx) => {
        const { albumId } = req.params;
        return res(ctx.json([createPhotoResponse(albumId)]));
    }),
];

const server = setupServer(...handlers);
export { server, rest };
