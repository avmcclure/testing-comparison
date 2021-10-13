import { PhotoAlbum } from "./PhotoAlbum";
import { get } from "./common/fetchService";
import { shallow } from "enzyme";
import { createPhotoResponse } from "./common/objectBuilders";
import { SearchInput } from "./SearchInput";

jest.mock("./common/fetchService");

describe("PhotoAlbum", () => {
    const photos = [createPhotoResponse(1)];
    get.mockResolvedValue(photos);

    describe("when component loads", () => {
        it("should render SearchInput component", async () => {
            const wrapper = shallow(<PhotoAlbum />);

            const searchInputComponent = wrapper.find("SearchInput");
            expect(searchInputComponent).toHaveLength(1);
        });

        it("should render Photos component with empty array", async () => {
            const wrapper = shallow(<PhotoAlbum />);

            const photosComponent = wrapper.find("Photos");
            expect(photosComponent).toHaveLength(1);
            expect(photosComponent.props().photos).toEqual([]);
        });
    });
});
